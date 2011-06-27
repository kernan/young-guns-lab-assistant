package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 * model for a course
 *   dependent on: labs
 *   dependent to: Instructor(User), Student(User)
 */

class Course {
	
	String name
	static hasMany = [labs: Lab]
	static belongsTo = [instructor: User]
	
	static constraints = {
		name(blank: false, unique: true)
	}
	
	static mapping = {
		name column: "`name`"
	}
	
	public int studentCount ()  {
		return StudentCourse.findAllByCourse(this)?.size() ?: 0
	}
}
