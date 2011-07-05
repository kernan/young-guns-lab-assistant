package edu.gatech.youngguns.labassistant

/**
 * 
 * @author William Dye
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
	
	def addToRandomTeam (Lab lab, User student) {
		if (!lab || !student) { return }
		for (team in lab.teams) {
			if (team.size() < lab.maxTeamSize) {
				team.addToStudents(student)
				return
			}
		}
		// if all the teams are full...
		Team t = new Team(name: "Team ${lab.teams.size() + 1}", lab: lab, students: [student])
		t.save(failOnError: true)
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
	
	def addIndividualTeam (Lab lab, User student) {
		if (!lab || !student) { return }
		Team t = new Team(name: "Team ${lab.teams?.size() + 1 ?: 1}", lab: lab, students: [student])
		t.save(failOnError: true)
	}
}
