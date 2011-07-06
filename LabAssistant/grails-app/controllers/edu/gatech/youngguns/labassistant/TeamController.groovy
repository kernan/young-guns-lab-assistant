package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author William Dye
 *
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
	 * 
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def create = {
		//TODO automatically assign lab if coming from team view page
		render(view: 'create', model: [labList: Lab.list()])
	}
	
	/**
	*
	* @Secured logged in fully, roles: Student
	*/
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
   def createTeam = {
	   render(view: 'createTeam', model: [lab: Lab.findById(params['lab'])])
   }
	
	/**
	 * 
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def save = {
		Lab lab = Lab.findById(params['lab'])
		String name = params['name']
		Team team = new Team(name: name, lab: lab)
		User student
		//add current user to team
		/*
		if(params['join']) {
			//TODO only add if they aren't already on a team
			student = springSecurityService.currentUser
			team.addToStudents(student)
		}*/
		team.save()
		redirect(action:'list')
	}
	
	/**
	 * select a team for students to join
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def join = {
		//TODO only show teams in the current lab
		render(view: 'join', model: [teamList: Team.list()])
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
