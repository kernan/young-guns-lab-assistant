package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Kyle Petrovich
 *
 * provides services that automatically assign students to teams
 */

class LabService {

    static transactional = false

	/**
	 * creates random teams of students of specified size
	 * @param lab the Lab to add random teams to
	 */
    def assignRandomTeams (Lab lab) {
		def students = StudentCourse.findAllByCourse(lab?.course)
		if (students) {
			//students = Collections.shuffle(students)
			int n = 1
			while (students) {
				Team t = new Team(name: "Team ${n++}", lab: lab, students: [])
				for (int i = 0; i < lab.maxTeamSize; i++) {
					if (students) { t.addToStudents(students.pop().student) }
				}
				t.save()
			}
		}
    }
	
	/**
	 * creates teams of students with 1 person per team
	 * @param lab the lab to add individual teams to
	 */
	def assignIndividualTeams(Lab lab) {
		def students = StudentCourse.findAllByCourse(lab?.course)
		if(students) {
			int n = 1
			while(students) {
				Team t = new Team(name: "Team ${n++}", lab: lab, students: [])
				t.addToStudents(students.pop().student)
				t.save()
			}
		}
	}
}
