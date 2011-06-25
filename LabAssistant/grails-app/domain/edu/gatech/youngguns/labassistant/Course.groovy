package edu.gatech.youngguns.labassistant

class Course {

	String name

	static constraints = {
		name blank: false, unique: true
	}

	static mapping = {
		name column: 'coursename'
	}

}