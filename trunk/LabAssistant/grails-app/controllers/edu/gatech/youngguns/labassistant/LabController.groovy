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
	 * redirects to save for Lab creation
	 * @Secured logged in fully, roles: Instructor, Admin
	 */
   	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def create = {
		render(view: 'create')
	}
	
	/**
	* create and save Lab from web form, then redirect to list
	* @Secured logged in fully, roles: Instructor, Admin
	*/
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def save = {
		//TODO create different types of labs
		//TODO get current course
		Course couse
		String name = params['name']
		Date startDate = params['startDate']
		Date endDate = params['endDate']
		//String description = params['description']
		User instructor
		def lab = new Lab(name: name, startDate: startDate, endDate: endDate/*, description: description*/)
		//TODO add the lab to the course
		lab.save()
		redirect(action:'list')
	}
}
