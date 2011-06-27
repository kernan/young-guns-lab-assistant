package edu.gatech.youngguns.labassistant

import java.util.Set;

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
