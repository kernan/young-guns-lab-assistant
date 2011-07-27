/**
* @author William Dye
*
* model for an Instructor (type of User)
*/

package edu.gatech.youngguns.labassistant

class Employee extends User {

    static hasMany = [sports: Sport]

    static constraints = {
    }
}
