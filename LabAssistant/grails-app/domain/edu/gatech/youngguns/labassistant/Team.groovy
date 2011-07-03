package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 * represents a Team of students
 */

class Team {
	
	String name
	static belongsTo = [lab: Lab]
	static hasMany = [students: User]
	int maxSize

    static constraints = {
		name(blank: false, unique: true)
		maxSize(blank: false)
    }
	
	static mapping = {
		relationships(cascade: 'delete')
	}
}
