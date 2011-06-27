package edu.gatech.youngguns.labassistant

import java.util.Set;
import org.aspectj.weaver.ResolvedType.SuperClassWalker;

/**
 * 
 * @author William Dye
 *
 */

class Student extends User {
	
	/**
	* Dependency injection for the springSecurityService.
	*/
	def springSecurityService
	
	static hasMany = [courses: Course]
	Set courses

    static constraints = {
    }
	
	/**
	 * 
	 * @param course
	 * @return
	 */
	def addCourse(Course course) {
		if(courses == null) {
			courses = new HashSet()
		}
		courses.add(course)
	}
}
