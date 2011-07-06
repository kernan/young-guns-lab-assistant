package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author William Dye
 *
 * controller for a User
 */

class UserController {
	
	def springSecurityService
	
	static defaultAction = 'index'
	
	/**
	 * default action
	 * @Secured logged in remembered, roles: Admin
	 */
	@Secured(["IS_AUTHENTICATED_REMEMBERED", "ROLE_ADMINISTRATOR"])
	def index = {
		list()
	}
	
	/**
	 * lists how many of each role exist, how many are logged in, and all Users from every role
	 * @Secured logged in remembered, roles: Admin
	 */
	@Secured(["IS_AUTHENTICATED_REMEMBERED", "ROLE_ADMINISTRATOR"])
	def list = {
		if (params['id']) {
			render(view: 'show', model: [user: User.findById(Integer.parseInt(params['id']))])
		}
		if (!springSecurityService.isLoggedIn()) {
			redirect(controller: 'login', action: 'auth')
		}
		Set users = User.list()
		int userCount = users.size()
		Role adminRole = Role.findByAuthority("ROLE_ADMINISTRATOR")
		Role instructorRole = Role.findByAuthority("ROLE_INSTRUCTOR")
		Role studentRole = Role.findByAuthority("ROLE_STUDENT")
		def adminList = User.adminList()
		int adminCount = adminList?.size() ?: 0
		int instructorCount = User.instructorList()?.size() ?: 0
		int studentCount = User.studentList()?.size() ?: 0
		render(view: 'list', model: [users: users, userCount: userCount, adminCount: adminCount,
			instructorCount: instructorCount, studentCount: studentCount, adminRole: adminRole, 
			instructorRole: instructorRole])
	}
	
	/**
	 * create a new User, redirects to save
	 * @Secured logged in fully, roles: Admin
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_ADMINISTRATOR"])
	def create = {
		render(view: 'create')
	}
	
	/**
	 * select what type of user to create
	 * @Secured logged in fully, roles: Admin
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_ADMINISTRATOR"])
	def selectType = {
		if (!params['type']) {
			render(view: 'create')
		}
		else if (params['type'] == "Administrator") {
			render(view: 'createAdmin')
		}
		else if (params['type'] == "Instructor") {
			render(view: 'createInstructor')
		}
		else if(params['type'] == "Student") {
			render(view: 'createStudent')
		}
	}
	
	/**
	 * create new user from prompts, redirects to list when done
	 * @Secured logged in fully, roles: Admin
	 */
	@Secured(["IS_AUTHENTICATED_FULLY", "ROLE_ADMINISTRATOR"])
	def save = {
		if (!params['type']) {
			render(view: 'create')
		}
		//create Aministrator
		if (params['type'] == 'admin') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Administrator admin = new Administrator(name: name, username: username, password: password, enabled: enabled)
			admin.save()
			UserRole.create(admin, Role.findByAuthority("ROLE_ADMINISTRATOR"))
			UserRole.create(admin, Role.findByAuthority("ROLE_INSTRUCTOR"))
			UserRole.create(admin, Role.findByAuthority("ROLE_STUDENT"))
		}
		//create Instructor
		else if (params['type'] == 'instructor') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Instructor instructor = new Instructor(name: name, username: username, password: password, enabled: enabled)
			instructor.save()
			UserRole.create(instructor, Role.findByAuthority("ROLE_INSTRUCTOR"))
		}
		//create Student
		else if (params['type'] == 'student') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Student student = new Student(name: name, username: username, password: password, enabled: enabled)
			student.save()
			UserRole.create(student, Role.findByAuthority("ROLE_STUDENT"))
		}
		redirect(action: 'list')
	}
 }