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
		Set users = User.list()
		int userCount = users.size()
		Role adminRole = Role.findByAuthority("ROLE_ADMINISTRATOR")
		Role instructorRole = Role.findByAuthority("ROLE_INSTRUCTOR")
		Role studentRole = Role.findByAuthority("ROLE_STUDENT")
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
			UserRole.create(instructor, Role.findByAuthority("ROLE_INSTRUCTOR"))
		}
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