package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 * model for a User's role
 */

class Role {

	String authority
	
	static constraints = {
		authority(blank: false, unique: true)
	}
	
	static mapping = {
		cache true
	}
}
