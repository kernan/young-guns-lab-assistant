package edu.gatech.youngguns.labassistant

import grails.test.*

class TeamControllerTests extends GrailsUnitTestCase {

    protected void setUp() {
        super.setUp()
		Team t1 = new Team(name: 'Yellow Jackets', id: 1)
		mockDomain(Team, [t1])
		User u1 = new User(username: 'psmith', name: 'Philip Smith', id: 1)
		User u2 = new User(username: 'wdye', name: 'William Dye', id: 2)
		mockDomain(User, [u1, u2])
		def tc = new TeamController()
    }

    protected void tearDown() {
        super.tearDown()
    }

    //tests
    
    /**
     * @author Robert Kernan
     */
    void testJoin() {

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
