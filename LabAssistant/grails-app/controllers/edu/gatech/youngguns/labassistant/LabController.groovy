package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author Robert Kernan
 *
 * controller for Lab
 *   creates, lists, and saves Lab objects
 */

class LabController {
	
	def springSecurityService
	def labService
	
   /**
    * lists all labs if logged in, otherwise redirects to login
    * @Secured logged in remembered, roles: all
    */
	@Secured(["IS_AUTHENTICATED_REMEMBERED"])
    def index = {
		list()
	}
	
	/**
	* list all labs
	* @Secured logged in remembered, roles: all
	*/
	@Secured(["IS_AUTHENTICATED_REMEMBERED"])
	def list = {
		//TODO only list labs within current course
		render(view: 'list', model: [labList: Lab.list(), labTotal: Lab.count()])
	}
	
	/**
	 * shows all teams associated with a certain lab
	 * @Secured logged in remembered, roles: all
	 */
	@Secured(["IS_AUTHENTICATED_REMEMBERED"])
	def show = {
		if (!params['lab']) {
			redirect(action: 'list')
		}
		render(view: 'show', model: [lab: Lab.findById(params['lab'] as long)])
	}
	
	/**
	 * redirects to save for Lab creation
	 * @Secured logged in fully, roles: Instructor, Admin
	 */
   	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def create = {
		if (Course.count() == 0) {
			redirect(controller: 'course', action: 'create')
		}
		else {
			def model = [:]
			if (springSecurityService.currentUser.hasRole("ROLE_ADMINISTRATOR")) {
				model['courseList'] = Course.list()
			} else { // instructor; only show courses they teach
				def courses = Course.findAllByInstructor(springSecurityService.currentUser)
				model['courseList'] = courses
			}
			render(view: 'create', model: model)
		}
	}
	
	/**
	 * 
	 * @Secured logged in fully, roles: Instructor
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def createLab = {
		render(view: 'createLab', model: [course: Course.findById(params['course'])])
	}
	
	/**
	* create and save Lab from web form, then redirect to list
	* @Secured logged in fully, roles: Instructor, Admin
	*/
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def save = {
		Course course = Course.findById(params['course'] as int)
		String name = params['name']
		Date startDate = params['startDate']
		Date endDate = params['endDate']
		String typeString = params['type']
		Lab.TeamType type = (typeString == "Individual" ? Lab.TeamType.INDIVIDUAL : typeString == "Random Select" ? Lab.TeamType.RANDOM : Lab.TeamType.SELF_SELECT)
		int teamSize
		if(type == Lab.TeamType.INDIVIDUAL) {
			teamSize = 1
		}
		else {
			teamSize = params['teamSize'] as int
		}
		//String description = params['description']
		User instructor
		def lab = new Lab(name: name, startDate: startDate, endDate: endDate, course: course, type: type, maxTeamSize: teamSize/*, description: description*/)
		lab.save(failOnError: true)
		if (type == Lab.TeamType.RANDOM) {
			labService.assignRandomTeams(lab)	
		}
		else if(type == Lab.TeamType.INDIVIDUAL) {
			labService.assignIndividualTeams(lab)
		}
		redirect(action:'list')
	}
}
