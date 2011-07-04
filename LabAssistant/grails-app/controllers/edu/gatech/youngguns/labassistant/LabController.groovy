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
   
   def report = {
   }
	
	/**
	 * redirects to save for Lab creation
	 * @Secured logged in fully, roles: Instructor, Admin
	 */
   	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def create = {
		if (Course.count() == 0) {
			render(controller: 'course', action: 'create')
		}
		render(view: 'create', model: [courseList: Course.list()])
	}
	
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
		int teamSize = params['teamSize'] as int
		String typeString = params['type']
		Lab.TeamType type = (typeString == "Individual" ? Lab.TeamType.INDIVIDUAL : typeString == "Random Select" ? Lab.TeamType.RANDOM : Lab.TeamType.SELF_SELECT)
		//String description = params['description']
		User instructor
		def lab = new Lab(name: name, startDate: startDate, endDate: endDate, course: course, type: type, maxTeamSize: teamSize/*, description: description*/)
		lab.save(failOnError: true)
		println "students in course = ${StudentCourse.findAllByCourse(course)?.getClass()}"
		if (type == Lab.TeamType.RANDOM) {
			labService.assignRandomTeams(lab)	
		}
		redirect(action:'list')
	}
}
