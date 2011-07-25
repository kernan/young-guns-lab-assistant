/**
* @author William Dye
*
* model for an Instructor (type of User)
*/

package edu.gatech.youngguns.labassistant

class Instructor extends User {

    static hasMany = [courses: Course]

    static constraints = {
    }
}
