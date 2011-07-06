package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 * model for an Administrator (type of User)
 */

class Administrator extends User {
	
	static hasMany = [courses: Course]
	
    static constraints = {
    }
}
