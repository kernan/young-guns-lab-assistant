package edu.gatech.youngguns.labassistant

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

/**
 * 
 * @author William Dye
 *
 */

class UserFilters {

	def springSecurityService
	
	/**
	 * 
	 */
    def filters = {
        all(controller:'*', action:'*') {
            after = { model ->
				if (model) {
					if (springSecurityService.isLoggedIn()) {
						model['currentUser'] = User.get(springSecurityService.principal.id)
					}
				} else {
					model = [currentUser: (springSecurityService.isLoggedIn() ? User.get(springSecurityService.principal.id) : null)]
				}
            }
        }
    } 
}
