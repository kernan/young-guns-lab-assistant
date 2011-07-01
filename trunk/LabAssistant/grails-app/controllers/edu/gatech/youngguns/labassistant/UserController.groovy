package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author William Dye
 *
 */

class UserController {
	
	def springSecurityService
	
	static defaultAction = 'index'
	
	/**
	 * default action
	 */
	def index = {
		if (!springSecurityService.isLoggedIn()) {
			redirect(controller: 'login', action: 'auth')
		}
		list()
	}
	
	/**
	 * lists how many of each role exist, how many are logged in, and all Users from every role
	 * @Secured restricted to: REMEMBERED USERS
	 */
	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	def list = {
		if (!springSecurityService.isLoggedIn()) {
			redirect(controller: 'login', action: 'auth')
		}
		Set users = User.list()
		int userCount = users.size()
		Role adminRole = Role.findByAuthority("ADMINISTRATOR")
		Role instructorRole = Role.findByAuthority("INSTRUCTOR")
		Role studentRole = Role.findByAuthority("STUDENT")
		def adminList = UserRole.findAllByRole(adminRole)
		int adminCount = adminList.size()
		def adminIds = []
		adminList.each { admin -> adminIds.add(admin.user.id) }
		def instructorList = UserRole.findAllByRole(instructorRole)
		int instructorCount = 0
		instructorList.each { instructor ->
			if (!adminIds.contains(instructor.user.id)) { instructorCount++ }
		}
		def studentList = UserRole.findAllByRole(studentRole)
		int studentCount = 0
		studentList.each { student ->
			if (!adminIds.contains(student.user.id)) { studentCount ++ }
		}
		render(view: 'list', model: [users: users, userCount: userCount, adminCount: adminCount,
			instructorCount: instructorCount, studentCount: studentCount, adminRole: adminRole, 
			instructorRole: instructorRole])
	}
	
	/**
	 * create a new User, redirects to save
	 */
	def create = {
		if (!springSecurityService.isLoggedIn()) {
			redirect(controller: 'login', action: 'auth')
		}
		if (session.currentUser.hasRole("ADMINISTRATOR")) {
			render(view: 'create')
		} else {
			redirect(controller: 'login', action: 'denied')
		}
	}
	
	/**
	 * select what type of user to create
	 */
	def selectType = {
		if (!params['type']) {
			render(view: 'create')
		}
		if (params['type'] == "Administrator") {
			render(view: 'createAdmin')
		}
		else if (params['type'] == "Instructor") {
			render(view: 'createInstructor')
		}
		else {
			render(view: 'createStudent')
		}
	}
	
	/**
	 * create new user from prompts, redirects to list when done
	 */
	def save = {
		if (!params['type']) {
			render(view: 'create')
		}
		Role adminRole = Role.findByAuthority("ADMINISTRATOR")
		Role instructorRole = Role.findByAuthority("INSTRUCTOR")
		Role studentRole = Role.findByAuthority("STUDENT")
		if (params['type'] == 'admin') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Administrator admin = new Administrator(name: name, username: username, password: password, enabled: enabled)
			admin.save()
			UserRole.create(admin, adminRole)
			UserRole.create(admin, instructorRole)
			UserRole.create(admin, studentRole)
		}
		else if (params['type'] == 'instructor') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Instructor instructor = new Instructor(name: name, username: username, password: password, enabled: enabled)
			instructor.save()
			UserRole.create(instructor, instructorRole)
		}
		else if (params['type'] == 'student') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Student student = new Student(name: name, username: username, password: password, enabled: enabled)
			student.save()
			UserRole.create(student, studentRole)
		}
		redirect(action: 'list')
	}
 }