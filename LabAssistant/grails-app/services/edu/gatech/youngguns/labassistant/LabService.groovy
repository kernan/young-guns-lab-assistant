package edu.gatech.youngguns.labassistant

class LabService {

    static transactional = false

    def assignRandomTeams (Lab lab) {
		def students = StudentCourse.findAllByCourse(lab?.course)/*?.toArray()*/
		if (students) {
			//students = Collections.shuffle(students) // randomize list
			int n = 1
			while (students) {
				Set teamMembers = []
				for (int i = 0; i < lab.maxTeamSize; i++) {
					if (students) { teamMembers << students.pop() }
				}
				Team t = new Team(name: "Team ${n++}", lab: lab, students: teamMembers)
				t.save()
			}
		}
    }
}
