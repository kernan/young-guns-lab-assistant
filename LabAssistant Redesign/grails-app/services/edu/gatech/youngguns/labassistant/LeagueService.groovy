/**
* @author William Dye
*
* provides services that automatically assign students to teams
*/

package edu.gatech.youngguns.labassistant

class LeagueService {
    
    static transactional = false
    
    //error return codes
    static final int fail_notcaptain = 1
    static final int fail_teamfull = 2
    static final int fail_inteam = 3
    //success return code
    static final int success = 0
    
    /**
     * adds a player to a given team
     * @param team the team to add given player to
     * @param player the player to add to given team
     * @return specific error codes on failure, success code otherwise
     */
    def int addToTeam(Team team, User player) {
        //check if current user is team captain
        def springSecurityService
        /*if(team.captain != springSecurityService.currentUser) {
            return fail_notcaptain
        }*/
        //check if team is full
        if(!(team.size() < team.capacity())) {
            return fail_teamfull
        }
        //check if player is already on a team in this league
        for(t in team.league.teams) {
            if(t.size() > 0) {
                if(t.contains(player)) {
                    return fail_inteam
                }
            }
        }
        //if passed all checks, add to team
        team.addToPlayers(player)
        team.save(failOnError: true)
        return success
    }
}
