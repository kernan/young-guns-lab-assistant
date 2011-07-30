package edu.gatech.youngguns.labassistant

import grails.test.*

class SportControllerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    //tests
    
    /**
     * @author Kyle Petrovich
     */
    void testAddLeague() {
        //setup user
        User user = new User(name: "User 1", username: "user1", password: "blahblahblah")
        mockDomain(User, [user])
        //setup domain objects
        Sport sport = new Sport(name: "Sport 1", maxTeamSize: 20)
        mockDomain(Sport, [sport])
        //save domain objects
        user.save(failOnError: 'true')
        sport.save(failOnError: 'true')
        //setup controller
        mockController(SportController)
        def controller = new SportController()
        controller.params.sport = sport.id
        controller.params.name = "Bush League"
        
        controller.addLeague()
        assertEquals(controller.success, controller.redirectArgs.action)
    }
    void testBlankName() {
        controller.params.name = ''
        controller.params.sport = sport.id
        controller.addLeague()
        assertEquals('/error', controller.response.redirectedUrl)
    }

    void testAssignCaptainInvalidUser() {
        controller.params.team = 'farts'
        controller.params.sport = 4
        controller.addLeague()
        assertNull(Sport.findById(4), sport)
        assertEquals('/error', controller.response.redirectedUrl)
    }
    

}
