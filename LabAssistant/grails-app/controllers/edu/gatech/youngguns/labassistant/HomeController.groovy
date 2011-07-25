/**
* @author William Dye
*
* controller for the home screen
*/

package edu.gatech.youngguns.labassistant

import edu.gatech.youngguns.labassistant.Role
import edu.gatech.youngguns.labassistant.User
import edu.gatech.youngguns.labassistant.UserRole

import grails.plugins.springsecurity.Secured

class HomeController {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    /**
     * Renders a main landing page ("control panel") based on the currently logged in user's roles.
     * Secured: logged in remembered, roles: all
     */
    @Secured(["IS_AUTHENTICATED_REMEMBERED"])
    def index = {
        //get current user's authorities
        def currentUserRoles = springSecurityService.currentUser.authorities
        if (currentUserRoles.contains(Role.findByAuthority("ROLE_ADMINISTRATOR"))) {
            //activate all admin views
            int activeUsers = User.findAllWhere(enabled:true).size()
            int lockedUsers = User.findAllWhere(accountLocked:true).size()
            int admins = User.adminList()?.size() ?: 0
            int instructors = User.instructorList()?.size() ?: 0
            int students = User.studentList()?.size() ?: 0
            int courses = Course.count().toInteger()
            Set adminCourses = Course.findAllByInstructor(User.get(springSecurityService.principal.id))
            int adminCourseCount = adminCourses.size()
            render(view: 'admin', model:[activeUsers:activeUsers, lockedUsers: lockedUsers, admins: admins,
                        instructors: instructors, students: students, courses: courses, adminCourses: adminCourses,
                        adminCourseCount: adminCourseCount])
        }
        else if (currentUserRoles.contains(Role.findByAuthority("ROLE_INSTRUCTOR"))) {
            //activate all instructor views
            Set courses = Course.findAllByInstructor(springSecurityService.currentUser)
            int courseCount = courses?.size() ?: 0
            render(view: 'instructor', model: [courses: courses, courseCount: courseCount])
        }
        else if (currentUserRoles.contains(Role.findByAuthority("ROLE_STUDENT"))) {
            //activate all student views
            Set courses = StudentCourse.findAllByStudent(springSecurityService.currentUser)
            int courseCount = courses.size()
            Set labs = Lab.findAllByEndDateGreaterThan(new Date())
            if (courses==null) {
                courses=0;
            }
            render(view: 'student', model:[studentcourses: courses, courseCount: courseCount, studentlabs: labs])
        }
        else {
            //not recognized authority
            render(view: '/index')
        }
    }
}
