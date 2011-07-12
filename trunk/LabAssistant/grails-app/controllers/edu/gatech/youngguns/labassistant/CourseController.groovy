package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author Kyle Petrovich
 * 
 * controller for a Course
 */

class CourseController {
	
	def labService

	 /**
	 * Dependency injection for the springSecurityService.
	 */
    def springSecurityService

    /**
	 * Default action; If the user is not logged in, redirects to login, otherwise displays
	 * all courses.
	 * @Secured logged in remembered, roles: all
	 */
	@Secured(["IS_AUTHENTICATED_REMEMBERED"])
    def index = {
	   list()
	}
	
   /**
    * List all courses.
    * list is different depending on current user's role
    * @Secured logged in remembered, roles: all
    */
	@Secured(["IS_AUTHENTICATED_REMEMBERED"])
   def list = {
	   //TODO: Add "join" link next to each one if Student.
	   if(springSecurityService.currentUser.hasRole("ROLE_ADMINISTRATOR")) {
		   render(view: 'list', model: [courseList: Course.list(), courseTotal: Course.count()])
	   }
	   else if(springSecurityService.currentUser.hasRole("ROLE_INSTRUCTOR")) {
	   		def courseList = Course.findAllByInstructor(springSecurityService.currentUser)
	   		render(view: 'list', model: [courseList: courseList, courseTotal: courseList.size()])
	   }
	   else if(springSecurityService.currentUser.hasRole("ROLE_STUDENT")) {
			render(view: 'list', model: [courseList: Course.list(), courseTotal: Course.count()]) 
			//Students can view and join any course.
	   }
	   else {
		   render(view: '/index')
	   }
	}
   
	/**
	 * Display create course page if user has proper permissions.
	 * @Secured logged in fully, roles: Instructor, Admin
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def create = {
		User currentUser = springSecurityService.currentUser
		if (currentUser.hasRole("ROLE_ADMINISTRATOR")) {
			def instructors = User.instructorList() + User.adminList()
			instructors.remove(currentUser)
			render(view: 'create', model: [instructors: instructors, userName: currentUser.name + " (me)"])
		} else {
			render(view: 'create')
		}
	}
	
	/**
	 * Save course object from form if coming from create, then redirect to list.
	 * @Secured logged in fully, roles: Instructor, Admin
     */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_INSTRUCTOR"])
	def save = {
		//TODO: Redirect to list if HTTPrequest didn't come from create?
		String name = params['name']
		User instructor
		if (params['instructor']) {
			instructor = (params['instructor'] == "THIS_GUY" ? springSecurityService.currentUser : User.get(params['instructor'] as long))
		}
		else {
			instructor = springSecurityService.currentUser
		}
		def course = new Course(name: name, instructor: instructor)
		course.save()
		redirect(action:'list')
	}
	
	/**
	 * Add a student to a course.
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def join = {
		Course course = Course.get(params['course'])
		if (!course) { redirect(view: 'list') }
		StudentCourse.create(springSecurityService.currentUser, course)
		for (lab in course.labs) {
			if (lab.type == Lab.TeamType.RANDOM) {
				labService.addToRandomTeam(lab, springSecurityService.currentUser)
			} else if (lab.type == Lab.TeamType.INDIVIDUAL) {
				labService.addIndividualTeam(lab, springSecurityService.currentUser)
			}
		}
		render view: 'success'
	}
	
	/**
	 * Show list of courses the student can enroll in.
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def enroll = {
		render(view: 'enroll', model: [courses: Course.list()])
	}
	
	/**
	 * Enroll the student in a course.
	 * @Secured logged in fully, roles: Student
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_STUDENT"])
	def enrollStudent = {
		if (!params['course']) {
			redirect(view: 'enroll')
		}
		StudentCourse.create(springSecurityService.currentUser, Course.get(params['course'] as long))
		render(view: 'success')
	}   
}