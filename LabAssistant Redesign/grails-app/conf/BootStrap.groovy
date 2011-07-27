import edu.gatech.youngguns.labassistant.Role
import edu.gatech.youngguns.labassistant.User
import edu.gatech.youngguns.labassistant.UserRole

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->
		
		// set up security roles if they don't already exist
		Role playerRole = Role.findByAuthority("ROLE_PLAYER") ?: new Role(authority: "ROLE_PLAYER").save(failOnError: true)
		Role employeeRole = Role.findByAuthority("ROLE_EMPLOYEE") ?: new Role(authority: "ROLE_EMPLOYEE").save(failOnError: true)
		Role adminRole = Role.findByAuthority("ROLE_ADMINISTRATOR") ?: new Role(authority: "ROLE_ADMINISTRATOR").save(failOnError: true)
		
		// set up a default admin user if none exists already
		User admin = User.findByUsername("admin") ?: new User(
			username: "admin", 
			password: springSecurityService.encodePassword("admin"),
			name: "Administrator", 
			enabled: true).save(failOnError: true)
			
		if (!admin.authorities) {
			UserRole.create(admin, adminRole)
			UserRole.create(admin, employeeRole)
			UserRole.create(admin, playerRole)
		}
    }
	
    def destroy = {
    }
}

