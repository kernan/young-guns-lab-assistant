package edu.gatech.youngguns.labassistant

import grails.plugins.springsecurity.Secured

class SportController {
    
    /**
     * adds a new league
     * @author Kyle Petrovich
     * Secured: logged in fully, roles: Employee
     */
    @Secured(["IS_AUTHENTICATED_FULLY", "ROLE_EMPLOYEE"])
    def addLeague = {
            User currentUser = springSecurityService.currentUser
            if (currentUser.hasRole("ROLE_EMPLOYEE")) {
                    String name = params['name']
                    Sport sport = params['sport']
                    if (!name) {
                        redirect(action: error, message: "invalid name")
                    }
                    if (!sport){
                        redirect(action: error, message: "invalid sport")
                    }
                    def league = new League(name: name, sport: sport)
                    league.save()
                    redirect(action:success);
            } 
    }
    def error = {
        redirect(action: list)
    }
    
    def success = {
        redirect(action: list)
    }
}
