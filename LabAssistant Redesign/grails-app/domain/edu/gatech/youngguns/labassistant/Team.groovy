/**
* @author Robert Kernan
*
* model for a Team of Students
*/

package edu.gatech.youngguns.labassistant

class Team {

    String name
    User captain

    static belongsTo = [league: League]
    static hasMany = [players: User]

    static constraints = {
        name(blank: false) // if we want unique names, we'll have to redo how we're naming random and individual teams
    }

    static mapping = {
        relationships(cascade: 'delete')
    }

    /**
     * accessor for the team capacity
     * @return the max size this team can be
     */
    public int capacity() {
        return this.league.sport.maxTeamSize
    }

    /**
     * accessor for team size
     * @return the current size of the team
     */
    public int size() {
        return this.players?.size() ?: 0
    }
    
    /**
     * checks if this team contains a given Player
     * @param player the Player to look for
     * @return true: player is in this team, false: player is not
     */
    public boolean contains(User player) {
        return this.players.contains(player)
    }
}
