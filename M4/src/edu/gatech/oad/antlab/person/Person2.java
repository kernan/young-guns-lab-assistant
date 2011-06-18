package edu.gatech.oad.antlab.person;
import java.util.Random;
/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Kyle Petrovich
 * @version 1.2
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
            String oldstring = input;
            System.out.println(oldstring);
            String newstring = "";
            Random randomizer = new Random();
            int position = 0;
            while (oldstring.length()!=0){
                //Pick a random character position, put the character there into the newstring and remove it.
                position = randomizer.nextInt(oldstring.length());
                newstring += oldstring.charAt(position);
                oldstring  = oldstring.substring(0,position) + oldstring.substring(position+1,oldstring.length());
                //iterate until the old string is depleted.
            }
	  return newstring;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
