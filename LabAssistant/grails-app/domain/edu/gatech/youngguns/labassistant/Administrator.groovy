package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 */

class Administrator extends User {
	
	static hasMany = [courses: Course]
	
    static constraints = {
    }
}
