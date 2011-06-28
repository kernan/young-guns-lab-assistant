package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 */

class User {

	String username
	String password
	String name
	boolean accountLocked
	boolean enabled
	boolean accountExpired
	boolean passwordExpired

	static constraints = {
		username(blank: false, unique: true)
		password(blank: false, minSize: 6)
		name(blank: false)
	}

	static mapping = {
		username column: "`username`"
		password column: "`password`"
		name column: "`name`"
		enabled column: "`enabled`"
		accountLocked column: "`locked`"
	}

	/**
	 * 
	 * @return
	 */
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
	
	boolean hasRole(String role) {
		if (role && role in ["ADMINISTRATOR", "INSTRUCTOR", "STUDENT"]) {
			return this.getAuthorities().contains(Role.findByAuthority(role))
		}
		return false
	}
	
	boolean hasAnyRole(String... roles) {
		if (roles) {
			for (role in roles) {
				if (!(role == "ADMINISTRATOR" || role == "INSTRUCTOR" || role == "STUDENT")) {
					return false
				}
				if (this.getAuthorities().contains(Role.findByAuthority(role))) {
					return true
				}
			}
			return false
		}
		return false
	}
}
