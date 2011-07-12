package edu.gatech.youngguns.labassistant

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * 
 * @author William Dye
 *
 * model for the associate between Students and Courses
 */

class StudentCourse implements Serializable {

	User student
	Course course
	
	static mapping = {
		id composite: ['student', 'course']
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
	 * @return true: they are equal, false: they are not
	 */
   boolean equals(other) {
	   if (!(other instanceof StudentCourse)) {
		   return false
	   }

	   other.student?.id == student?.id &&
		   other.course?.id == course?.id
   }

   /**
	* finds a StudentCourse with given studentId and courseId
	* @param studentId the id of the student to search for
	* @param courseId the id of the course to search for
	* @return the StudentCourse with both the given studentId and courseId
	*/
   static StudentCourse get(long studentId, long courseId) {
	   find 'from StudentCourse where student.id=:studentId and course.id=:courseId',
		   [studentId: studentId, courseId: courseId]
   }

   /**
	* create a StudentCourse with given student and course
	* @param student the student to associate with a course
	* @param course the course to associate with a student
	* @return the StudentCourse with both the given student and course
	*/
   static StudentCourse create(User student, Course course, boolean flush = false) {
	   new StudentCourse(student: student, course: course).save(flush: flush, insert: true)
   }

   /**
	* removes a StudentCourse with given student and course
	* @param student the student to find
	* @param course the course to find
	* @return true: the delete was successful, false: it was not
	*/
   static boolean remove(User student, Course course, boolean flush = false) {
	   StudentCourse instance = StudentCourse.findByStudentAndCourse(student, course)
	   instance ? instance.delete(flush: flush) : false
   }

   /**
	* remove all StudentCourse containing the given student
	* @param student the value to remove all containing
	*/
   static void removeAll(User student) {
	   executeUpdate 'DELETE FROM StudentCourse WHERE student=:student', [student: student]
   }

   /**
	* remove all StudentCourse containing all the given course
	* @param role the value to remove all containing
	*/
   static void removeAll(Course course) {
	   executeUpdate 'DELETE FROM StudentCourse WHERE course=:course', [course: course]
   }
}
