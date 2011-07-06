package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 */

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
