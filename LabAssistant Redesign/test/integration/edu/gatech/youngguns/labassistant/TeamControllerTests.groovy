package edu.gatech.youngguns.labassistant

import grails.test.*

class TeamControllerTests extends ControllerUnitTestCase {

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    //tests
    
    /**
     * @author Robert Kernan
     */
    void testJoinSuccess() {
        def leagueService = new LeagueService()
        //setup users
        User user1 = new User(name: "User 1", username: "user1", password: "blahblahblah")
        User user2 = new User(name: "User 2", username: "user2", password: "blahblahblah")
        mockDomain(User, [user1, user2])
        //setup domain objects
        Sport sport1 = new Sport(name: "Sport 1", maxTeamSize: 20)
        mockDomain(Sport, [sport1])
        League league1 = new League(name: "League 1")
        mockDomain(League, [league1])
        Team team1 = new Team(name: "Team 1", captain: user1)
        mockDomain(Team, [team1])
        //setup relationships
        league1.addToTeams(team1)
        league1.sport = sport1
        team1.league = league1
        //save domain objects
        user1.save(failOnError: 'true')
        user2.save(failOnError: 'true')
        team1.save(failOnError: 'true')
        //setup controller
        mockController(TeamController)
        def controller = new TeamController()
        controller.params.team = team1.id
        controller.params.player = user2.id
        
        controller.join()
        assertEquals(controller.success, controller.redirectArgs.action)
    }
    
    /**
     * @author Robert Kernan
     */
    void testJoinFullTeam() {
        def leagueService = new LeagueService()
        //setup users
        User user1 = new User(name: "User 1", username: "user1", password: "blahblahblah")
        User user2 = new User(name: "User 2", username: "user2", password: "blahblahblah")
        mockDomain(User, [user1, user2])
        //setup domain objects
        Sport sport1 = new Sport(name: "Sport 1", maxTeamSize: 1)
        mockDomain(Sport, [sport1])
        League league1 = new League(name: "League 1")
        mockDomain(League, [league1])
        Team team1 = new Team(name: "Team 1", captain: user1)
        mockDomain(Team, [team1])
        //setup relationships
        team1.addToPlayers(user2)
        league1.addToTeams(team1)
        league1.sport = sport1
        team1.league = league1
        //save domain objects
        user1.save(failOnError: 'true')
        user2.save(failOnError: 'true')
        team1.save(failOnError: 'true')
        //setup controller
        mockController(TeamController)
        def controller = new TeamController()
        controller.params.team = team1.id
        controller.params.player = user2.id
        
        controller.join()
        assertEquals(controller.error, controller.redirectArgs.action)
    }
    
    /**
     * @author Robert Kernan
     */
    void testJoinAlreadyInTeam() {
        def leagueService = new LeagueService()
        //setup users
        User user1 = new User(name: "User 1", username: "user1", password: "blahblahblah")
        User user2 = new User(name: "User 2", username: "user2", password: "blahblahblah")
        mockDomain(User, [user1, user2])
        //setup domain objects
        Sport sport1 = new Sport(name: "Sport 1", maxTeamSize: 20)
        mockDomain(Sport, [sport1])
        League league1 = new League(name: "League 1")
        mockDomain(League, [league1])
        Team team1 = new Team(name: "Team 1", captain: user1)
        mockDomain(Team, [team1])
        //setup relationships
        team1.addToPlayers(user2)
        league1.addToTeams(team1)
        league1.sport = sport1
        team1.league = league1
        //save domain objects
        user1.save(failOnError: 'true')
        user2.save(failOnError: 'true')
        team1.save(failOnError: 'true')
        //setup controller
        mockController(TeamController)
        def controller = new TeamController()
        controller.params.team = team1.id
        controller.params.player = user2.id
        
        controller.join()
        assertEquals(controller.error, controller.redirectArgs.action)
    }
    
    /**
     * @author William Dye
     */
    void testAssignCaptainSuccess() {
		tc.params.team = '1'
		tc.params.captain = '2'
		tc.assignCaptain()
		assertEquals(u2, t1.captain)
    }

    /**
     * @author William Dye
     */
    void testAssignCaptainInvalidTeam() {
		tc.params.team = '4'
		tc.params.captain = '2'
		tc.assignCaptain()
		assertEquals('/error', tc.response.redirectedUrl)
    }

	/**
     * @author William Dye
     */
	void testAssignCaptainInvalidUser() {
		tc.params.team = '1'
		tc.params.captain = '100'
		tc.assignCaptain()
		assertNull(Team.findById(1).captain)
		assertEquals('/error', tc.response.redirectedUrl)	
	}

	/**
     * @author William Dye
     */
	void testAssignCaptainWhenThereAlreadyIsOne() {
		t1.captain = u1
		tc.params.team = '1'
		tc.params.captain = '2'
		tc.assignCaptain()
		assertEquals(u1, t1.captain)
		assertEquals('/error', tc.response.redirectedUrl)
	}
}
