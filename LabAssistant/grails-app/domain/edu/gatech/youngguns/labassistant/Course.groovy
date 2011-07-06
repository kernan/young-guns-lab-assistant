package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 * model for a course
 */

class Course {
	
	String name
	
	static belongsTo = [instructor: User]
	static hasMany = [labs: Lab]
	
	static constraints = {
		name(blank: false, unique: true)
	}
	
	static mapping = {
		name(column: "`name`")
		relationships(cascade: 'delete')
	}
	
	public int studentCount ()  {
		return StudentCourse.findAllByCourse(this).size()
	}
}
