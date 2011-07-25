import edu.gatech.youngguns.labassistant.Role
import edu.gatech.youngguns.labassistant.User
import edu.gatech.youngguns.labassistant.UserRole

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->
		
		// set up security roles if they don't already exist
		Role studentRole = Role.findByAuthority("ROLE_STUDENT") ?: new Role(authority: "ROLE_STUDENT").save(failOnError: true)
		Role instructorRole = Role.findByAuthority("ROLE_INSTRUCTOR") ?: new Role(authority: "ROLE_INSTRUCTOR").save(failOnError: true)
		Role adminRole = Role.findByAuthority("ROLE_ADMINISTRATOR") ?: new Role(authority: "ROLE_ADMINISTRATOR").save(failOnError: true)
		
		// set up a default admin user if none exists already
		User admin = User.findByUsername("admin") ?: new User(
			username: "admin", 
			password: springSecurityService.encodePassword("admin"),
			name: "Administrator", 
			enabled: true).save(failOnError: true)
			
		if (!admin.authorities) {
			UserRole.create(admin, adminRole)
			UserRole.create(admin, instructorRole)
			UserRole.create(admin, studentRole)
		}
    }
	
    def destroy = {
    }
}

