package edu.gatech.youngguns.labassistant.securityplugin

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import grails.plugins.springsecurity.Secured

/**
 * 
 * @author William Dye
 *
 */

class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 * @Secured restricted to: none
	 */
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def index = {
		// TODO  put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
