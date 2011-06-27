package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 * model for a Lab
 *   CURRENTLY ONLY A PLACEHOLDER
 */

class Lab {
	
	String name

	static constraints = {
		name(blank: false, unique: true)
	}
	
	static mapping = {
		name column: "`name`"
	}
}
