package edu.gatech.youngguns.labassistant

/**
 * 
 * @author Robert Kernan
 * 
 * checks lab team types
 */

class LabTagLib {
	
	/**
	 * check if given lab has individual teams
	 */
	def ifIndividualLab = { attrs, body ->
		def lab = attrs["lab"]
		if(lab.type == Lab.TeamType.INDIVIDUAL) {
			//out << "Individual"
			out << body()
		}
	}
	
	/**
	 * check if given lab has random teams
	 */
	def ifRandomLab = { attrs, body ->
		def lab = attrs["lab"]
		if(lab.type == Lab.TeamType.RANDOM) {
			//out << "Random"
			out << body()
		}
	}
	
	/**
	 * check if given lab has Self-Selected teams
	 */
	def ifSelfSelectLab = { attrs, body ->
		def lab = attrs["lab"]
		if(lab.type == Lab.TeamType.SELF_SELECT) {
			out << "Self-Select"
			//out << body()
		}
	}
}
