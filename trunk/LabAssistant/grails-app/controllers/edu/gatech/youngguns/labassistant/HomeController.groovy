package edu.gatech.youngguns.labassistant

import edu.gatech.youngguns.labassistant.Role
import edu.gatech.youngguns.labassistant.User
import edu.gatech.youngguns.labassistant.UserRole

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author William Dye
 *
 */

class HomeController {

	/**
	* Dependency injection for the springSecurityService.
	*/
	def springSecurityService
	
	/**
	 * Renders a main landing page ("control panel") based on the currently logged in user's roles.
	 * @Secured restricted to: none
	 */
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index = {
		if (!springSecurityService.isLoggedIn()) {
			redirect(controller: 'login', action: 'auth')
		}
		else {
			//get current user's authorities
			if (session.currentUser.hasRole("ADMINISTRATOR")) {
				//activate all admin views
				Role adminRole = Role.findByAuthority("ADMINISTRATOR")
				Role instructorRole = Role.findByAuthority("INSTRUCTOR")
				Role studentRole = Role.findByAuthority("STUDENT")
				int activeUsers = User.findAllWhere(enabled:true).size()
				int lockedUsers = User.findAllWhere(accountLocked:true).size()
				def adminList = UserRole.findAllByRole(adminRole)
				int admins = adminList.size()
				def adminIds = []
				adminList.each { admin -> adminIds.add(admin.user.id) }
				def instructorList = UserRole.findAllByRole(instructorRole)
				int instructors = 0
				instructorList.each { instructor ->
					if (!adminIds.contains(instructor.user.id)) { instructors++ }
				}
				def studentList = UserRole.findAllByRole(studentRole)
				int students = 0
				studentList.each { student ->
					if (!adminIds.contains(student.user.id)) { students ++ }
				}
				int courses = Course.count().toInteger()
				Set adminCourses = Course.findAllByInstructor(session.currentUser)
				int adminCourseCount = adminCourses.size()
				render(view: 'admin', model:[activeUsers:activeUsers, lockedUsers: lockedUsers, admins: admins,
					instructors: instructors, students: students, courses: courses, adminCourses: adminCourses, 
					adminCourseCount: adminCourseCount])
			}
			else if (session.currentUser.hasRole("INSTRUCTOR")) {
				//activate all instructor views
				Set courses = Course.findAllByInstructor(session.currentUser)
				int courseCount = courses.size()
				render(view: 'instructor', model:[courses: courses, courseCount: courseCount])
			}
			else if (session.currentUser.hasRole("STUDENT")) {
				//activate all student views
				render(view: 'student', model:[])
			}
			else {
				//not recognized authority
				render(view: '/index')
			}
		}
	}
}
