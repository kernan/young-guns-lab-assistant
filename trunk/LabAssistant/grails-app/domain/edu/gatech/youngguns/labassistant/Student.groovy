package edu.gatech.youngguns.labassistant

class Student extends User {
	
	static hasMany = [courses: Course]

    static constraints = {
    }
}
