package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

class LeagueController {
     
    /**
    * Render the create team page
    * @author Philip Smith
    * Secured: logged in fully, roles: Player
    */
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_PLAYER"])
   def createTeam = {
       render(view: 'createTeam', model: [league: League.findById(params['league'])])
   }

   /**
    * Adds a new team
    * @author Philip Smith
    * Secured: logged in fully, roles: Player
    */
   @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_PLAYER"])
   def addTeam = {
       League league = League.findById(params['league'])
       String name = params['name']
       String captain = params['captain']
       
       if (!league) {
           redirect(action: error, message: "invalid league")
       }
       if (!name) {
           redirect(action: error, message: "invalid name")
       }
       if (!captain){
           redirect(action: error, message: "invalid captain")
       }
       Team team = new Team(name: name, league: league)
       team.assignCaptain(captain)
       
       for(player in params['players']) {
           team.join(player)
       }
     
       team.save()
       redirect(action:success)
   }
  
   def error = {
       redirect(action: list)
   }
   
   def success = {
       redirect(action: list)
   }

}