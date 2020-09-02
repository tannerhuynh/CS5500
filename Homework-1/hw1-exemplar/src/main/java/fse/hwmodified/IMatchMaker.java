/**
 * 
 */
package fse.hwmodified;

import java.util.List;

/**
 * The contract or specification for MatchMaker.  The intent is to sort a set of 
 * people into two groups, and then find a set of matches.  The groups do not need to 
 * be equal in size.  Each person may specify a preference for people in the other group
 * for matching, but the matching may not respect those choices or rank orderings.
 * 
 * @author micha
 * @version 1.0    9/21/2019
 */

public interface IMatchMaker {

	/**
	 * Find a set of matches for two sets of people (Person)
	 */
	
	public void makeMatches();
	
	
	/**
	 * Initializes a Matchmaker by sorting a list of Person into two groups,
	 * the proposers and the proposees.
	 * 
	 * @param people
	 * @param proposerType
	 * @param proposeeType
	 */
	public void setUpGroups(List<Person> people, Attributes proposerType, Attributes proposeeType);
	
	
	/**
	 * returns the list of people (Person) in a particular matching group.
	 * 
	 * @param groupName
	 * @return
	 */
	public List<Person> getList(String groupName);
		
}

