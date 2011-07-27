/**
* @author William Dye
*
* model for a User in the system
*/

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
        if (role && role in [
            "ROLE_ADMINISTRATOR",
            "ROLE_EMPLOYEE",
            "ROLE_PLAYER"
        ]) {
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
                if (!(role == "ROLE_ADMINISTRATOR" || role == "ROLE_EMPLOYEE" || role == "ROLE_PLAYER")) {
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
    static Set employeeList () {
        def adminList = adminList()
        def adminIds = [], employees = [], employeeList = []
        adminList.each { admin -> adminIds.add(admin.id) }
        UserRole.findAllByRole(Role.findByAuthority("ROLE_EMPLOYEE")).each { employee -> employees += employee.user }
        employees.each { employee ->
            if (!adminIds.contains(employee.id)) {
                employeeList += employee
            }
        }
        return employeeList
    }

    /**
     * gives a list of all Students
     * @return list of students
     */
    static Set playerList () {
        def adminList = adminList()
        def adminIds = [], players = [], playerList = []
        adminList.each { admin -> adminIds.add(admin.id) }
        UserRole.findAllByRole(Role.findByAuthority("ROLE_PLAYER")).each { player -> players += player.user }
        players.each { player ->
            if (!adminIds.contains(player.id)) {
                playerList += player
            }
        }
        return playerList
    }

    /**
     * checks if this User is a member of a given team
     * @param team the team to check membership of
     * @return true: this user is a member, false: this user is not
     */
    public boolean isMemberOfTeam (Team team) {
        return team.players.contains(this)
    }

    /**
     * checks if this User is a member of any team in a given lab
     * @param lab the lab to check all team membership in
     * @return true: this user is a member of a team in this lab, false: this user is not 
     */
    public boolean isMemberOfAnyTeam (League league) {
        for (team in league.teams) {
            if (this.isMemberOfTeam(team)) {
                return true
            }
        }
        return false
    }
}
