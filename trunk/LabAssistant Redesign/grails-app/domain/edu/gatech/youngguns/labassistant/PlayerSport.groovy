/**
* @author William Dye
*
* model for the associate between Students and Courses
*/

package edu.gatech.youngguns.labassistant

import org.apache.commons.lang.builder.HashCodeBuilder

class PlayerSport implements Serializable {

    User player
    Sport sport

    static mapping = {
        id composite: ['player', 'sport']
        version false
    }

    /**
     * build hash code for this student course relationship
     * @return the hash code for this student course relationship
     */
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (student) builder.append(student.id)
        if (course) builder.append(course.id)
        builder.toHashCode()
    }

    /**
     * checks equality between StudentCourse objects
     * @param other the StudentCourse to compare to
     * @return true: they are equal, false: they are not
     */
    boolean equals(other) {
        if (!(other instanceof PlayerSport)) {
            return false
        }

        other.player?.id == player?.id &&
                other.sport?.id == sport?.id
    }

    /**
     * finds a StudentCourse with given studentId and courseId
     * @param studentId the id of the student to search for
     * @param courseId the id of the course to search for
     * @return the StudentCourse with both the given studentId and courseId
     */
    static PlayerSport get(long playerId, long sportId) {
        find 'from PlayerSport where player.id=:playerId and sport.id=:sportId',
                [playerId: playerId, sportId: sportId]
    }

    /**
     * create a StudentCourse with given student and course
     * @param student the student to associate with a course
     * @param course the course to associate with a student
     * @return the StudentCourse with both the given student and course
     */
    static PlayerSport create(User player, Sport sport, boolean flush = false) {
        new PlayerSport(player: player, sport: sport).save(flush: flush, insert: true)
    }

    /**
     * removes a StudentCourse with given student and course
     * @param student the student to find
     * @param course the course to find
     * @return true: the delete was successful, false: it was not
     */
    static boolean remove(User player, Sport sport, boolean flush = false) {
        PlayerSport instance = PlayerSport.findByPlayerAndSport(player, sport)
        instance ? instance.delete(flush: flush) : false
    }

    /**
     * remove all StudentCourse containing the given student
     * @param student the value to remove all containing
     */
    static void removeAll(User player) {
        executeUpdate 'DELETE FROM PlayerSport WHERE player=:player', [player: player]
    }

    /**
     * remove all StudentCourse containing all the given course
     * @param role the value to remove all containing
     */
    static void removeAll(Sport sport) {
        executeUpdate 'DELETE FROM PlayerSport WHERE sport=:sport', [sport: sport]
    }
}
