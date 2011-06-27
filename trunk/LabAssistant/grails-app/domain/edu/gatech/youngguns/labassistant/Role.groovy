package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
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
