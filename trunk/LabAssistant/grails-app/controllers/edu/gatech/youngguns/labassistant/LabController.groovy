package edu.gatech.youngguns.labassistant

class LabController {
	
	/**
	* Dependency injection for the springSecurityService.
	*/
    def springSecurityService

   /**
    * default action, redirects to list
    */
    def index = {
		//TODO add permission authentication
		redirect action: 'list'
	}
	
	/**
	 * create a new lab
	 */
	def create = {
		//TODO add permission authentication
		return
	}
	
	/**
	 * list all labs
	 */
	def list = {
		//TODO add permission authentication
		return [labList: Lab.list(), labTotal: Lab.count()]
	}
	
	/**
	* Save course object from form if coming from create, then redirect to list.
	*/
   def save = {
	   String name = params['name']
	   User instructor
	   //TODO add the lab to the course
	   def lab = new Lab(name: name, instructor: instructor)
	   lab.save()
	   redirect action:'list'
   }
}
