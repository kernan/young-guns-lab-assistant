package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
 *
 * model for a User in the system
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
	 * accessor for the user's roles
	 * @return this user's authorities
	 */
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
	
	/**
	 * checks if the user has the given authority
	 * @param role authority to check if the user has
	 * @return true: the user has the authority, false: it does not
	 */
	boolean hasRole(String role) {
		if (role && role in ["ROLE_ADMINISTRATOR", "ROLE_INSTRUCTOR", "ROLE_STUDENT"]) {
			return this.getAuthorities().contains(Role.findByAuthority(role))
		}
		return false
	}
	
	/**
	 * check if the user has any of the given roles
	 * @param roles a list of roles to check
	 * @return true: has any of the given roles, false: does not
	 */
	boolean hasAnyRole(String... roles) {
		if (roles) {
			for (role in roles) {
				if (!(role == "ROLE_ADMINISTRATOR" || role == "ROLE_INSTRUCTOR" || role == "ROLE_STUDENT")) {
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
	
	/**
	 * accessor for a list of all Administrators
	 * @return list of administrators
	 */
	static Set adminList () {
		Set adminList = []
		UserRole.findAllByRole(Role.findByAuthority("ROLE_ADMINISTRATOR")).each { admin -> adminList += admin.user }
		return adminList
	}
	
	/**
	 * accessor for a list of all Instructors
	 * @return list of instructors
	 */
	static Set instructorList () {
		def adminList = adminList()
		def adminIds = [], instructors = [], instructorList = []
		adminList.each { admin -> adminIds.add(admin.id) }
		UserRole.findAllByRole(Role.findByAuthority("ROLE_INSTRUCTOR")).each { instructor -> instructors += instructor.user }
		instructors.each { instructor ->
			if (!adminIds.contains(instructor.id)) { instructorList += instructor }
		}
		return instructorList
	}
	
	/**
	 * accessor fo a list of all Students
	 * @return list of students
	 */
	static Set studentList () {
		def adminList = adminList()
		def adminIds = [], students = [], studentList = []
		adminList.each { admin -> adminIds.add(admin.id) }
		UserRole.findAllByRole(Role.findByAuthority("ROLE_STUDENT")).each { student -> students += student.user }
		students.each { student ->
			if (!adminIds.contains(student.id)) { studentList += student }
		}
		return studentList
	}
	
	public boolean isMemberOfTeam (Team team) {
		return team.students.contains(this)
	}
	
	public boolean isMemberOfAnyTeam (Lab lab) {
		for (team in lab.teams) {
			if (this.isMemberOfTeam(team)) {
				return true
			}
		}
		return false
	}
}
