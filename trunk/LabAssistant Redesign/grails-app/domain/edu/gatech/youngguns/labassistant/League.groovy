/**
* @author Robert Kernan
*
* model for a Lab
*/

package edu.gatech.youngguns.labassistant

class League {

    String name
    Date startDate
    Date endDate
    static hasMany = [teams: Team]
    static belongsTo = [sport: Sport]

    static constraints = {
        name(blank: false, unique: true)
        sport(nullable: true)
    }

    static mapping = {
        relationships(cascade: 'delete')
    }
}
