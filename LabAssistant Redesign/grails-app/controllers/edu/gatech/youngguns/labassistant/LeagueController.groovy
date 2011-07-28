package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

class LeagueController {
    
    /**
     * adds a new team
     * @author Philip Smith
     * Secured: logged in fully, roles: Player
     */
    @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_PLAYER"])
    def addTeam = {
        
    }

}
