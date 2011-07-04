package edu.gatech.youngguns.labassistant

class LabService {

    static transactional = false

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
}
