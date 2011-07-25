/**
* @author William Dye
*
* model for a Student (type of User)
*/

package edu.gatech.youngguns.labassistant

import java.util.Set;
import org.aspectj.weaver.ResolvedType.SuperClassWalker;

class Student extends User {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    static constraints = {
    }
}
