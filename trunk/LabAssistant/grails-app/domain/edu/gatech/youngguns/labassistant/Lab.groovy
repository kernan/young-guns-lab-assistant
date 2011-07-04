package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 */

class Lab {
	
	enum TeamType {
		RANDOM, SELF_SELECT, INDIVIDUAL
	}
	
	String name
	int maxTeamSize
	TeamType type
	Date startDate
	Date endDate
	
	static belongsTo = [course: Course]
	static hasMany = [teams: Team]

	static constraints = {
		name(blank: false, unique: true)
	}
	
	static mapping = {
		relationships(cascade: 'delete')
	}
}
