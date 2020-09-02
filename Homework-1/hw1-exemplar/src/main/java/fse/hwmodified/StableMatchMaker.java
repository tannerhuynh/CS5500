package fse.hwmodified;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* 
* Represents the Matchmaker, which matches proposers with proposees.  
* This uses the Gale-Shipley algorithm.  This finds the most preferred set of matches
* for all participants.
* 
* It is possible that a Person in either group may not get a partner.
* 
* @author micha
* @version 1  9/21/2019
*
*/
public class StableMatchMaker implements IMatchMaker {
	
	/**
	 * returns a the list of Person in one of the match groups.
	 * 
	 * @param side  - which match group to return.  Must be either groupA or groupB
	 */
	@Override
	public List<Person> getList(String side) {

		if (!side.contentEquals("groupA") && !side.contentEquals("groupB")) {
			throw new IllegalArgumentException("Must be groupA or groupB - not " + side);
		}
		
		if (side.contentEquals("groupA")) {
			return groupA;
		}
		else if (side.contentEquals("groupB")) {
			return groupB;
		}
		else
			return new ArrayList<>();
	}

	/**
	 * Initializes a Matchmaker by sorting a list of Person into two groups,
	 * the proposers and the proposees.
	 * 
	 * @param people
	 * @param groupAType
	 * @param groupBType
	 */
	@Override
	public void setUpGroups(List<Person> people, Attributes groupAType, Attributes groupBType) {
		
		try { 
			validateSetUpGroupsInputs(people, groupAType, groupBType);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		Iterator<Person> personIterator = people.iterator();
		
		while (personIterator.hasNext()) {
			
			Person person = personIterator.next();
			
			if (person.getAttribute() == groupAType) {
				groupA.add(person);
			}
			else if (person.getAttribute() == groupBType) {
				groupB.add(person);
			}
			else {
				throw new IllegalArgumentException("List of candidates has more than two groups.");
			}
		}
	}

	private void validateSetUpGroupsInputs(List<Person> people, Attributes groupAType, Attributes groupBType) {

		if (people.isEmpty()) {
			throw new IllegalArgumentException("Empty list of people.");
		}
		
		if (groupAType == groupBType) {
			throw new IllegalArgumentException("GroupAType and GroupBType shouldn't be the same.");
		}
	}

	//
	// implements interface via Gale-Shapley algorithm
	//
	@Override
	public void makeMatches() {

		Person unpairedProposer = unpaired(groupA);
		
		while (unpairedProposer != null) { 
			
			if (unpairedProposer.getPreferences().isEmpty()) {
				
				/*
				 * if matchee is out of choices, remove Person from pool.
				 * This means every other Person in groupB prefers someone else.
				 */
				
				groupA.remove(unpairedProposer);
				
				unpairedProposer = unpaired(groupA); /* move on to the next Person */
				continue;
			}

			String topChoice = unpairedProposer.getPreferences().get(0);				
			
			unpairedProposer.removePreference(topChoice);
			
			Person otherPerson;
				
			try {
				otherPerson = getNextChoice(topChoice);
			}
			catch (IllegalStateException e) {
				continue;  // skip choice if person not found
			}
			
			if (matched(otherPerson)) {

				if (prefers(otherPerson, unpairedProposer)) {
					
					breakCurrentMatch(otherPerson); 
					match(unpairedProposer, otherPerson); 
				}
			}
			else { 
				
				match(unpairedProposer, otherPerson); 
			}
			
			unpairedProposer = unpaired(groupA);  /* move on to the next Person */
		}
	}
	
	/**
	 * Matches two Person together.
	 * 
	 * @param a  first Person
	 * @param b	 second Person
	 */
	private void match(Person a, Person b) {

		a.setMatch(b);
		b.setMatch(a);
	}

	/**
	 * Breaks the match of a Person
	 * 
	 * @param otherPerson  the Person whose heart will be broken
	 */
	private void breakCurrentMatch(Person otherPerson) {

		otherPerson.getMatch().setMatch(null);
	}

	/**
	 * Identifies if a person currently matched prefers someone else.
	 * 
	 * @param thisPerson    the person currently matched
	 * @param newPerson     someone else to consider as a partner
	 * @return boolean - true if the other Person prefers this unpaired person
	 */
	private boolean prefers(Person thisPerson, Person newPerson) {
		
		Person currentMatch = thisPerson.getMatch();
		
		int rankOfCurrentMatch = thisPerson.getPreferences().indexOf(currentMatch.getName());
		int rankOfUnpairedProposer = thisPerson.getPreferences().indexOf(newPerson.getName());
		
		return rankOfCurrentMatch > rankOfUnpairedProposer;
	}

	/**
	 * Identifies if someone has already been matched
	 * 
	 * @param p  the Person
	 * @return boolean - true if the person is matched
	 */
	private boolean matched(Person p) {
		
		return (p.getMatch() != null) ;
	}

	/**
	 * Returns the next unmatched person in someone's preferred list
	 *
	 * @param people the list of Person
	 * @return the next unmatched person, if one exists.
	 */
	private Person unpaired(List<Person> people) {
		
		return people.stream()
				.filter(p -> p.getMatch() == null)
				.findAny()
		  		.orElse(null);
	}
	
	/**
	 * Looks up a Person by name and finds the corresponding Person instance
	 * 
	 * @param name  the person's name
	 * @return Person   the instance corresponding to name, if it exists
	 */
	private Person getNextChoice(String name) {
		
		Person person = groupB.stream()
				  .filter(p -> p.getName().equals(name))
				  .findAny()
				  .orElse(null);
		
		if (person == null)
			throw new IllegalStateException("next choice not found based on name");
		
		return person;
	}

	/**
	 * one of the two groups of Person to match
	 */
	private List<Person> groupA = new ArrayList<>();
	
	/**
	 * the other of the two groups of Person to match
	 */
	private List<Person> groupB = new ArrayList<>();

}
