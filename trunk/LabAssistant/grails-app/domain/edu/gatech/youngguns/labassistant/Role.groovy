package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 * model for a User's role
 */

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority(blank: false, unique: true)
	}
}
