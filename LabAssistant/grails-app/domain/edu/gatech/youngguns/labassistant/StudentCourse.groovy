package edu.gatech.youngguns.labassistant

import org.apache.commons.lang.builder.HashCodeBuilder

class StudentCourse implements Serializable {

	Student student
	Course course
	
	/**
	*
	*/
   boolean equals(other) {
	   if (!(other instanceof StudentCourse)) {
		   return false
	   }

	   other.student?.id == student?.id &&
		   other.course?.id == course?.id
   }

   /**
	*
	*/
   int hashCode() {
	   def builder = new HashCodeBuilder()
	   if (student) builder.append(student.id)
	   if (course) builder.append(course.id)
	   builder.toHashCode()
   }

   /**
	*
	* @param studentId
	* @param courseId
	* @return
	*/
   static StudentCourse get(long studentId, long courseId) {
	   find 'from StudentCourse where student.id=:studentId and course.id=:courseId',
		   [studentId: studentId, courseId: courseId]
   }

   /**
	*
	* @param student
	* @param course
	* @return
	*/
   static StudentCourse create(Student student, Course course, boolean flush = false) {
	   new StudentCourse(student: student, course: course).save(flush: flush, insert: true)
   }

   /**
	*
	* @param student
	* @param course
	* @return
	*/
   static boolean remove(Student student, Course course, boolean flush = false) {
	   StudentCourse instance = StudentCourse.findByStudentAndCourse(student, course)
	   instance ? instance.delete(flush: flush) : false
   }

   /**
	*
	* @param student
	*/
   static void removeAll(Student student) {
	   executeUpdate 'DELETE FROM StudentCourse WHERE student=:student', [student: student]
   }

   /**
	*
	* @param role
	*/
   static void removeAll(Course course) {
	   executeUpdate 'DELETE FROM StudentCourse WHERE course=:course', [course: course]
   }
   
   /**
	*
	*/
   static mapping = {
	   id composite: ['student', 'course']
	   version false
   }
}
