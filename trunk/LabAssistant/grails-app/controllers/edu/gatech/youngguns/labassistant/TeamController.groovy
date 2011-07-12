package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author William Dye
 *
 * controller for a Team
 */
class TeamController {
	
	def springSecurityService
	
	/**
	* lists all teams if logged in, otherwise redirects to login
	* @Secured logged in remembered, roles: all
	*/
	@Secured(["IS_AUTHENTICATED_REMEMBERED"])
	def index = {
		list()
	}
	
	/**
	 * show how many teams exists
	 * @Secured logged in remembered, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_REMEMBERED", "ROLE_STUDENT"])
    def list = {
		render(view: 'list', model: [teams: Team.count()])
	}
	
	/**
	 * show a list of labs to create a team for
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def create = {
		//TODO automatically assign lab if coming from team view page
		if (springSecurityService.currentUser.hasRole("ROLE_ADMINISTRATOR")) {
			render(view: 'create', model: [labList: Lab.findAllByType(Lab.TeamType.SELF_SELECT)])
		}
		else if (springSecurityService.currentUser.hasRole("ROLE_STUDENT")) {
			def courseLabs = []
			def studentCourses = StudentCourse.findAllByStudent(springSecurityService.currentUser)
			for (course in studentCourses) {
				course = course.course
				courseLabs += course.labs
			}
			courseLabs.flatten()
			def labList = []
			for (lab in courseLabs) {
				if (!springSecurityService.currentUser.isMemberOfAnyTeam(lab) && lab.type == Lab.TeamType.SELF_SELECT) {
					labList += lab
				}
			}
			//TODO render some text if the student isn't enrolled in any courses with self-select labs
			render(view: 'create', model: [labList: labList])
		}
	}
	
	/**
	* render create team page
	* @Secured logged in fully, roles: Student
	*/
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
   def createTeam = {
	   render(view: 'createTeam', model: [lab: Lab.findById(params['lab'])])
   }
	
	/**
	 * create and save a new team to the database
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def save = {
		Lab lab = Lab.findById(params['lab'])
		String name = params['name']
		Team team = new Team(name: name, lab: lab)
		User student
		//add current user to team
		team.save()
		redirect(action:'list')
	}
	
	/**
	 * 
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def chooseLab = {
		def selfSelectlabs = Lab.findAllByType(Lab.TeamType.SELF_SELECT)
		def labs = []
		for (lab in selfSelectlabs) {
			if (StudentCourse.findByStudentAndCourse(springSecurityService.currentUser, lab.course)) {
				labs += lab
			}
		}
		render(view: 'chooseLab', model: [labs: labs])
	}
	
	/**
	 * select a team for students to join
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def join = {
		//TODO only show teams in the current lab
		if (!params['lab']) {
			redirect(view: 'chooseLab')
		} else {
			Lab lab = Lab.get(params['lab'])
			render(view: 'join', model: [teamList: lab.teams])
		}
	}
	
	/**
	 * add the student to the selected team
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def joinTeam = {
		boolean teamNotFull = true
		boolean notInLab = true
		Team team = Team.findById(params['team'] as int)
		User student = springSecurityService.currentUser
		//only add if they aren't already in a team
		if(team.size() < team.lab.maxTeamSize) {
			for(t in team.lab.teams) {
				for(s in t.students) {
					if(s == student) {
						notInLab = false
					}
				}
			}
			if(notInLab) {
				team.addToStudents(student)
			}
		}
		else {
			teamNotFull = false
		}
		//display proper success/error screen (in order of importance)
		if(!notInLab) {
			render(view: 'error', model: [message: "You're already on a team in this lab."])
		}
		else if(!teamNotFull) {
			render(view:'error', model: [message: "That team is full."])
		}
		else {
			render(view:'success')
		}
	}
}
