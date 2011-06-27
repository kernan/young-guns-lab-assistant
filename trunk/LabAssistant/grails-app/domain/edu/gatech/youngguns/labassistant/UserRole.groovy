package edu.gatech.youngguns.labassistant

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * 
 * @author William Dye
 *
 */

class UserRole implements Serializable {

	User user
	Role role

	/**
	 * 
	 */
	boolean equals(other) {
		if (!(other instanceof UserRole)) {
			return false
		}

		other.user?.id == user?.id &&
			other.role?.id == role?.id
	}

	/**
	 * 
	 */
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	static UserRole get(long userId, long roleId) {
		find 'from UserRole where user.id=:userId and role.id=:roleId',
			[userId: userId, roleId: roleId]
	}

	/**
	 * 
	 * @param user
	 * @param role
	 * @return
	 */
	static UserRole create(User user, Role role, boolean flush = false) {
		new UserRole(user: user, role: role).save(flush: flush, insert: true)
	}

	/**
	 * 
	 * @param user
	 * @param role
	 * @return
	 */
	static boolean remove(User user, Role role, boolean flush = false) {
		UserRole instance = UserRole.findByUserAndRole(user, role)
		instance ? instance.delete(flush: flush) : false
	}

	/**
	 * 
	 * @param user
	 */
	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserRole WHERE user=:user', [user: user]
	}

	/**
	 * 
	 * @param role
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
