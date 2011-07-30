package edu.gatech.youngguns.labassistant

import grails.test.*

class LeagueControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    //tests
    
    /**
    * @author Philip Smith
    */
   void testAddTeam() {
       controller.params.league= '1'
       controller.params.captain = '2'
       controller.params.name = 'Saul Goode'
       controller.addTeam()
       assertEquals(controller.success, controller.redirectArgs.action)
   }
    
    /**
    * @author Philip Smith
    */
   void testAddTeamInvalidLeague() {
       controller.params.league= ''
       controller.params.captain = '2'
       controller.params.name = 'HAY'
       controller.addTeam()
       assertEquals(controller.error, controller.redirectArgs.action)
   }


   /**
    * @author Philip Smith
    */
   void testAddTeamInvalidName() {
       controller.params.league= '1'
       controller.params.captain = '2'
       controller.params.name = ''
       controller.addTeam()
       assertEquals(controller.error, controller.redirectArgs.action)
   }

   /**
    * @author Philip Smith
    */
   void testAddTeamInvalidCaptain() {
       controller.params.league= '1'
       controller.params.captain = ''
       controller.params.name = 'Saul Goode'
       controller.addTeam()
       assertEquals(controller.error, controller.redirectArgs.action)
   }
}
