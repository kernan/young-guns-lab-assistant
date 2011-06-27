package edu.gatech.youngguns.labassistant

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
		   redirect action:'list'
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
	   return [courseList: Course.list(), courseTotal: Course.count()]
	   
   }
   
   /**
    * Display create course page if user has proper permissions.
    */
   def create = {
	   //TODO: Restrict this page to instructors and Sysadmins only.
	   return
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