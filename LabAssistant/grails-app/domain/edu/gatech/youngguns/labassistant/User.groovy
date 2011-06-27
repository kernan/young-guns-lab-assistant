package edu.gatech.youngguns.labassistant

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

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}