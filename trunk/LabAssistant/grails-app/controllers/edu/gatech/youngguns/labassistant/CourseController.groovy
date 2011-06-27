package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author Kyle Petrovich
 *
 */

class CourseController {

	/**
	* Dependency injection for the springSecurityService.
	*/
   def springSecurityService

   /**
	* Default action; If the user is not logged in, redirects to login, otherwise displays
	* all courses.
	*/
   def index = {
	   if (springSecurityService.isLoggedIn()) {
		   redirect(action:'list')
	   }
	   else {
		   redirect(controller:'login',action:'auth')
	   }
   }
   
   /**
    * List all courses.
    */
   def list = {
	   //TODO: Add "join" link next to each one if Student.
	   if (!springSecurityService.isLoggedIn()) {
		   redirect(controller: 'login', action: 'auth')
	   }
	   def currentUserRoles = User.get(springSecurityService.principal.id).getAuthorities()
	   if (currentUserRoles.contains(Role.findByAuthority("ADMINISTRATOR"))) {
		   render(view: 'list', model: [courseList: Course.list(), courseTotal: Course.count()])
	   }
	   else if (currentUserRoles.contains(Role.findByAuthority("INSTRUCTOR"))) {
	   		def courseList = User.get(springSecurityService.principal.id).courses
	   		render(view: 'list', model: [courseList: courseList, courseTotal: courseList.size()])
	   }
	   else {
	   		render(text: 'Under Construction')
	   		// TODO: make something happen for students
	   }
   }
   
   /**
    * Display create course page if user has proper permissions.
    * @Secured can only be accessed by: ADMINISTRATOR, INSTRUCTOR
    */
   @Secured(["hasRole(['ADMINISTRATOR', 'INSTRUCTOR'])"])
   	def create = {
	   render(view:'create')
   }
   
   /**
    * Save course object from form if coming from create, then redirect to list.
    */
   	def save = {
	   //TODO: Redirect to list if HTTPrequest didn't come from create?
	   String name = params['name']
	   User instructor
	   if (params['instructor']) { 
		   instructor = User.findByName(params['instructor'])
	   }
	   else {
	   		instructor = User.get(springSecurityService.principal.id)
	   }
	   def course = new Course(name: name, instructor: instructor)
	   course.save()
	   redirect action:'list'
   }
}