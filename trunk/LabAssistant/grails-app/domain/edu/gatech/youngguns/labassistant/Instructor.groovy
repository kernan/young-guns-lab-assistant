package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 */

class Instructor extends User {
	
	static hasMany = [courses: Course]

	static constraints = {
	}
}
