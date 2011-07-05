package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 *
 */

class UserTagLib {
	
	def springSecurityService
	
	def getUserName = { attrs, body ->
		def currentUser = User.get(springSecurityService.principal.id)
		out << currentUser.name
	}
}
