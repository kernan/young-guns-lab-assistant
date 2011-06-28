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
		int adminCount = UserRole.findAllByRole(adminRole).size()
		int instructorCount = UserRole.findAllByRole(instructorRole).size()
		int studentCount = UserRole.findAllByRole(studentRole).size()
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
		User currentUser = User.get(springSecurityService.principal.id)
		if (currentUser.hasRole("ADMINISTRATOR")) {
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
		if (params['type'] == 'admin') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Administrator admin = new Administrator(name: name, username: username, password: password, enabled: enabled)
			admin.save()
			UserRole.create(admin, Role.findByAuthority("ADMINISTRATOR"))
		}
		else if (params['type'] == 'instructor') {
			String name = params['name']
			String username = params['username']
			def password = springSecurityService.encodePassword(params['password1'])
			def enabled = params['enabled']
			Instructor instructor = new Instructor(name: name, username: username, password: password, enabled: enabled)
			instructor.save()
			UserRole.create(instructor, Role.findByAuthority("INSTRUCTOR"))
		}
		redirect(action: 'list')
	}
 }