package edu.gatech.youngguns.labassistant

class Lab {
	/**
	* Dependency injection for the springSecurityService.
	*/
    def springSecurityService
   
	String name

    /**
	 * constraints for Course params
	 */
	static constraints = {
		name(blank: false, unique: true)
	}
	
	/**
	 * mapping for tables containing Courses
	 */
	static mapping = {
		name column: "`name`"
	}
	
	/**
	 * checks equality based on Course name
	 */
	boolean equals(other) {
		return this.name.equals(other.getName())
	}
	
	/**
	 * outputs Course name
	 */
	String toString() {
		return this.name
	}
}
