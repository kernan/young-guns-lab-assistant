package edu.gatech.youngguns.labassistant

class UserFilters {

	def springSecurityService
	
    def filters = {
        all(controller:'*', action:'*') {
            after = { model ->
				if (model && springSecurityService.isLoggedIn()) {
					model['user'] = User.get(springSecurityService.principal.id)
				}
                
            }
        }
    } 
}
