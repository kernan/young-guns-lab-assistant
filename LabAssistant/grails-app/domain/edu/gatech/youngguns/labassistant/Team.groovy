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
		name(blank: false) // if we want unique names, we'll have to redo how we're naming random and individual teams
    }
	
	static mapping = {
		relationships(cascade: 'delete')
	}
	
	public int capacity () {
		return this.lab.maxTeamSize
	}
	
	public int size () {
		return this.students?.size() ?: 0
	}
}

