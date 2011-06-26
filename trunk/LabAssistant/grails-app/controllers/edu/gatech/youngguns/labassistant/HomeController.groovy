package edu.gatech.youngguns.labassistant

import edu.gatech.youngguns.labassistant.Role
import edu.gatech.youngguns.labassistant.User
import edu.gatech.youngguns.labassistant.UserRole

class HomeController {

	def springSecurityService
	
    def index = {
		if (!springSecurityService.isLoggedIn()) {
			redirect(controller: 'login', action: 'auth')
		}
		def currentUserRoles = User.get(springSecurityService.principal.id).getAuthorities()
		if (currentUserRoles.contains(Role.findByAuthority("ADMINISTRATOR"))) {
			int activeUsers = User.findAllWhere(enabled:true).size()
			int lockedUsers = User.findAllWhere(accountLocked:true).size()
			int admins = UserRole.findAllWhere(role:Role.findByAuthority("ADMINISTRATOR")).size()
			int instructors = UserRole.findAllWhere(role:Role.findByAuthority("INSTRUCTOR")).size()
			int students = UserRole.findAllWhere(role:Role.findByAuthority("STUDENT")).size()
			render(view: 'admin', model:[activeUsers:activeUsers, lockedUsers: lockedUsers, admins: admins,
				instructors: instructors, students: students])
		} else if (currentUserRoles.contains(Role.findByAuthority("INSTRUCTOR"))) {
			render(view: 'instructor', model:[])
		} else if (currentUserRoles.contains(Role.findByAuthority("STUDENT"))) {
			render(view: 'student', model:[])
		} else {
			render(view: '/index')
		}
	}
}
