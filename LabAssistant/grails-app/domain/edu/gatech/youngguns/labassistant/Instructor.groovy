package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 * model for an Instructor (type of User)
 */

class Instructor extends User {
	
	static hasMany = [courses: Course]

	static constraints = {
	}
}
