package edu.gatech.youngguns.labassistant

class TeamController {

	static defaultAction = 'list'
	
    def list = {
		render(view: 'list', model: [teams: Team.count()])
	}
}
