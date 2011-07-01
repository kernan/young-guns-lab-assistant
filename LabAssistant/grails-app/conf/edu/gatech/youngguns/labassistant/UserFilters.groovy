package edu.gatech.youngguns.labassistant

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class UserFilters {

	def springSecurityService
	
    def filters = {
        all(controller:'*', action:'*') {
            after = { model ->
				if (model && springSecurityService.isLoggedIn()) {
					model['currentUser'] = User.get(springSecurityService.principal.id)
				}
            }
        }
		currentUser(uri: CH.config.login.success.defaultUrl) {
			before = { ->
            	if (springSecurityService.isLoggedIn() && !session['currentUser']) {
					session['currentUser'] = User.get(springSecurityService.principal.id)
				}
			}
        }
    } 
}
