package edu.gatech.youngguns.labassistant

class Course {
	
	/**
	* Dependency injection for the springSecurityService.
	*/
    def springSecurityService
	
	String name
	User instructor
	Set<Student> students
	Set<Lab> labs
	
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
		instructor column: "`instructor`"
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
