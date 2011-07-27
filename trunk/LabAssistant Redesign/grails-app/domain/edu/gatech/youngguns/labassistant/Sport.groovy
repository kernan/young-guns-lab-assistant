/**
* @author Robert Kernan
*
* model for a course
*/

package edu.gatech.youngguns.labassistant

class Sport {

    String name

    static belongsTo = [employee: Employee]
    static hasMany = [leagues: League]

    static constraints = {
        name(blank: false, unique: true)
    }

    static mapping = {
        name(column: "`name`")
        relationships(cascade: 'delete')
    }

    /**
     * finds number of StudentCourse relationships associated with this course
     * @return the number of relationships
     */
    public int playerCount ()  {
        return PlayerSport.findAllBySport(this).size()
    }
}
