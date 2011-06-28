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
    */
    def index = {
		if(springSecurityService.isLoggedIn()) {
			redirect(action: 'list')
		}
		else {
			redirect(controller: 'auth', action: 'login')
		}
	}
	
	/**
	* list all labs
	*/
   def list = {
	   //TODO only list labs within current course
	   return [labList: Lab.list(), labTotal: Lab.count()]
   }
	
	/**
	 * redirects to save for Lab creation
	 */
	def create = {
		redirect(action: 'save')
	}
	
	/**
	* create and save Lab from web form, then redirect to list
	*/
   def save = {
	   String name = params['name']
	   User instructor
	   //TODO add the lab to the course
	   def lab = new Lab(name: name, instructor: instructor)
	   lab.save()
	   redirect(action:'list')
   }
}
