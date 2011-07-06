package edu.gatech.youngguns.labassistant

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * 
 * @author William Dye
 *
 * model for the relationship between Users and Roles
 */

class UserRole implements Serializable {

	User user
	Role role

	/**
	 * checks if two instances of UserRole are equal
	 * @return true: they are equal, false: they are not
	 */
	boolean equals(other) {
		if (!(other instanceof UserRole)) {
			return false
		}

		other.user?.id == user?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	/**
	 * finds a UserRole contaning the given role and user
	 * @param userId the role to search for
	 * @param roleId the student to search for
	 * @return the UserRole containg the given user and role
	 */
	static UserRole get(long userId, long roleId) {
		find 'from UserRole where user.id=:userId and role.id=:roleId',
			[userId: userId, roleId: roleId]
	}

	/**
	 * creats a UserRole containing the given user and role
	 * @param user the user to associate with this UserRole
	 * @param role the role to assiciate with this UserRole
	 * @return a new UserRole containing the given role and user
	 */
	static UserRole create(User user, Role role, boolean flush = false) {
		new UserRole(user: user, role: role).save(flush: flush, insert: true)
	}

	/**
	 * removes a UserRole containing the given user and role
	 * @param user the user to remove association of
	 * @param role the role to remove association of
	 * @return true: was removed successfully, false: was not
	 */
	static boolean remove(User user, Role role, boolean flush = false) {
		UserRole instance = UserRole.findByUserAndRole(user, role)
		instance ? instance.delete(flush: flush) : false
	}

	/**
	 * removes all UserRoles containing the given user
	 * @param user the user to look for
	 */
	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserRole WHERE user=:user', [user: user]
	}

	/**
	 * removes all UserRoles containing the given role
	 * @param role the role to search for
	 */
	static void removeAll(Role role) {
		executeUpdate 'DELETE FROM UserRole WHERE role=:role', [role: role]
	}

	/**
	 * 
	 */
	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}
