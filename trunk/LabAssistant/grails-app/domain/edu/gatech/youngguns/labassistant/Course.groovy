package edu.gatech.youngguns.labassistant

import org.apache.commons.lang.builder.HashCodeBuilder

class Course {
	
	def springSecurityService
	
	String name
	User instructor
	Set students
	Set labs
	
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
