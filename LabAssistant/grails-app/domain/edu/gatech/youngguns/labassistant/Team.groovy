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

    static constraints = {
		name(blank: false)
    }
	
	static mapping = {
		relationships(cascade: 'delete')
	}
	
	public int capacity () {
		return this.lab.maxTeamSize
	}
}

