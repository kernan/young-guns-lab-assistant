/**
* @author Robert Kernan
*
* provides GSP functionality to access current user details
*/

package edu.gatech.youngguns.labassistant

class UserTagLib {

    def springSecurityService

    /**
     * gets the current user's name
     */
    def getUserName = { attrs, body ->
        def currentUser = User.get(springSecurityService.principal.id)
        out << currentUser.name
    }
}
