import edu.gatech.youngguns.labassistant.Role
import edu.gatech.youngguns.labassistant.User
import edu.gatech.youngguns.labassistant.UserRole

class BootStrap {

	def springSecurityService
	
    def init = { servletContext ->
		
		// set up security roles if they don't already exist
		Role studentRole = Role.findByAuthority("STUDENT") ?: new Role(authority: "STUDENT").save(failOnError: true)
		Role instructorRole = Role.findByAuthority("INSTRUCTOR") ?: new Role(authority: "INSTRUCTOR").save(failOnError: true)
		Role adminRole = Role.findByAuthority("ADMINISTRATOR") ?: new Role(authority: "ADMINISTRATOR").save(failOnError: true)
		
		// set up a default admin user if none exists already
		User admin = User.findByUsername("admin") ?: new User(
			username: "admin", 
			password: springSecurityService.encodePassword("admin"),
			name: "Administrator", 
			enabled: true).save(failOnError: true)
		if (!admin.authorities.contains("ADMINISTRATOR")) {
			UserRole.create(admin, adminRole)
		}
    }
    def destroy = {
    }
}
