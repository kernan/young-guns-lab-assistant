package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

class TeamController {
    
    /**
    * add a player to a selected team
    * @author Robert Kernan
    * Secured: logged in fully, roles: Player
    */
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_PLAYER"])
   def join = {
       Team team = Team.findById(params['team'] as int)
       User player = Player.findById(params['player'] as int)
       def LeagueService
       int code = LeagueService.addToTeam(team, player)
       if(code != LeagueService.success) {
           redirect(action: error, message: "generic & useless error message")
       }
       else {
           redirect(action: success)
       }
   }
   
   /**
    * assign a team captain
    * @author William Dye
    * Secured: logged in fully, roles: Player
    */
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_PLAYER"])
   def assignCaptain = {
       
   }
}
