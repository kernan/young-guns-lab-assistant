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
	Set labs
	static belongsTo = [instructor: Instructor, students: Student]
	
	static constraints = {
		name(blank: false, unique: true)
	}
	
	static mapping = {
		name column: "`name`"
	}
}
