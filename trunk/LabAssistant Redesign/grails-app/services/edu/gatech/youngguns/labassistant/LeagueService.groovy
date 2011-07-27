/**
* @author William Dye
*
* provides services that automatically assign students to teams
*/

package edu.gatech.youngguns.labassistant

class LeagueService {
    
    static transactional = false
    
    /**
     * 
     * @param league
     * @param player
     * @return
     */
    def addToTeam(League league, User player) {
        if (!league || !player) {
            return
        }
        Team t = new Team(name: "Team ${league.teams?.size() + 1 ?: 1}", league: league, players: [player])
        t.save(failOnError: true)
    }
}
