package edu.gatech.youngguns.labassistant.securityplugin

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * 
 * @author William Dye
 *
 */

class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO  put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
