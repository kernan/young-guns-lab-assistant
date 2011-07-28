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
        
    }
}
