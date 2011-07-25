/**
* @author William Dye
*
* model for a User's role
*/

package edu.gatech.youngguns.labassistant

class Role {

    String authority

    static constraints = {
        authority(blank: false, unique: true)
    }

    static mapping = {
        cache true
    }
}
