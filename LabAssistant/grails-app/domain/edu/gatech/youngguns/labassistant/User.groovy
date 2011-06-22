package edu.gatech.youngguns.labassistant

class User {

	String username
	String password
	boolean accountLocked
	/* boolean enabled
	boolean accountExpired
	boolean passwordExpired */

	static constraints = {
		username blank: false, unique: true
		password blank: false, minSize: 6
	}

	static mapping = {
		username column: 'username'
		password column: '`password`'
		accountLocked column: 'locked'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
}
