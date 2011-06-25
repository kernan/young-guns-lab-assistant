package edu.gatech.youngguns.labassistant

class Course {

	String coursename

	static constraints = {
		coursename blank: false, unique: true
	}

	static mapping = {
		coursename column: 'coursename'
	}

}