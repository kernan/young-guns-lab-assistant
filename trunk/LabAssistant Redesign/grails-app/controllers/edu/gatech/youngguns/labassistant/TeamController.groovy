package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

class TeamController {

   def leagueService
    
    /**
    * add a player to a selected team
    * @author Robert Kernan
    * Secured: logged in fully, roles: Player
    */
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_PLAYER"])
   def join = {
       Team team = Team.findById(params['team'] as int)
       User player = User.findById(params['player'] as int)
       int code = leagueService.addToTeam(team, player)
       if(code != leagueService.success) {
           if(code == leagueService.fail_notcaptain) {
               redirect(action: error, message: "current user is not captain")
           }
           else if(code == leagueService.fail_teamfull) {
               redirect(action: error, message: "team is full")
           }
           else if(code == leagueService.fail_inteam) {
               redirect(action: error, message: "player already in team")
           }
           else {
               redirect(action: error, message: "unknown")
           }
       }
       else {
           team.save()
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
      	Team team = Team.findById(params['team'] as int)
    	User captain = Player.findById(params['captain'] as int)
    	if (!team) {
    		redirect(action: error, message: "invalid team")
    	}
    	if (team.captain) {
    		redirect(action: error, message: "team already has a captain")
    	}
    	if (!captain) {
    		redirect(action: error, message: "invalid captain")
    	}
    	team.captain = captain
    	render(view: list)
   }
   
   def error = {
       redirect(action: list)
   }
   
   def success = {
       redirect(action: list)
   }
}
