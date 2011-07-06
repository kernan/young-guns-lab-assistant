package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 * model for a Lab
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
	static hasMany = [teams: Team]
	static belongsTo = [course: Course]

	static constraints = {
		name(blank: false, unique: true)
		course(nullable: true)
	}
	
	static mapping = {
		relationships(cascade: 'delete')
	}
}
