package fse.hwmodified;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * Represents the Matchmaker, which matches proposers with proposees.  
 * 
 * The two sets do not have to be balanced and proposers do not have consider all 
 * proposees.  If either is true, it is possible that a Person on either side may 
 * not get a partner.  This implementation uses a greedy approach, so the matches
 * made may not be optimal for Person in either group.
 * 
 * @author micha
 * @version 1.0  9/21/2019
 *
 */
public class MatchMaker implements IMatchMaker {

	/**
	 *   attempts to create a match for each proposer.
	 *   
	 *   Assumes the two sets of Person to be matched have been set-up.
	 */
	
	public void makeMatches() {

		warnIfUnbalancedMatchSets();  /* informative method, not functional */
		
		Iterator<Person> proposerIterator = proposers.iterator();
		
		while (proposerIterator.hasNext()) {
			
			Person proposer = proposerIterator.next();
			
			findPartner(proposer);
			
			if (notMatched(proposer)){
				log.info(proposer.getName() + " did not find a match.\n");
			}
		}
	}

	/**
	 * find the first available and most desired Person in a proposer's list.  If such a Person exists,
	 * match them.
	 * 
	 * @param proposer
	 */
	private void findPartner(Person proposer) {

		log.info("\nConsidering " + proposer.getName() + " as proposer.");
		
		Iterator<String> preferenceIterator = proposer.getPreferences().iterator();
		
		while (notMatched(proposer) && preferenceIterator.hasNext()) {
			
			Person otherPerson = getNextChoice(preferenceIterator.next());
			
			if (otherPerson == null) {
				log.warn("Didn't find candidate in list");
				throw new IllegalStateException("next choice not found based on name");
			}
			
			log.info("Considering " + otherPerson.getName() + " as proposee.");
			
			if (notMatched(otherPerson)) {
				matchThem(proposer, otherPerson);
			}
			else {
				log.info("Unfortunately, " + otherPerson.getName() + " is already matched.");
			}
		}
	}

	/**
	 * Connects two Person as matched.
	 * 
	 * @param proposer
	 * @param otherPerson
	 */
	private void matchThem(Person proposer, Person otherPerson) {
		
		otherPerson.setMatch(proposer);
		
		proposer.setMatch(otherPerson);
		
		log.info(proposer.getName() + " is now matched with " + otherPerson.getName() + ".\n");
	}

	private boolean notMatched(Person p) {
		
		return (p.getMatch() == null);
	}

	/**
	 * returns the Person object associated with a name in the list of proposees.
	 * 
	 * @param interest
	 * @return
	 */
	
	private Person getNextChoice(String name) {
		
		return proposees.stream()
				  .filter(p -> p.getName().equals(name))
				  .findAny()
				  .orElse(null);
	}
	
	
	private void warnIfUnbalancedMatchSets() {
		
		if (proposers.size() > proposees.size()) {
			log.debug("Some proposers will not get matched.");
		}
		else if (proposers.size() < proposees.size()){
			log.debug("Some proposees will not get matched.");
		}
		else {
			log.debug("There's someone for everyone.");
		}
	}
	
	
	@Override
	public List<Person> getList(String side) {

		if (!side.contentEquals("proposers") && !side.contentEquals("proposees")) {
			throw new IllegalArgumentException("Must be proposers or proposees - not " + side);
		}
		
		if (side.contentEquals("proposers")) {
			return proposers;
		}
		else if (side.contentEquals("proposees")) {
			return proposees;
		}
		else
			return new ArrayList<>();
	}

	/**
	 * Initializes a Matchmaker by sorting a list of Person into two groups,
	 * the proposers and the proposees.
	 * 
	 * @param people
	 * @param proposerType
	 * @param proposeeType
	 */
	@Override
	public void setUpGroups(List<Person> people, Attributes proposerType, Attributes proposeeType) {
		
		try { 
			validateSetUpGroupsInputs(people, proposerType, proposeeType);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
		Iterator<Person> personIterator = people.iterator();
		
		while (personIterator.hasNext()) {
			
			Person person = personIterator.next();
			
			if (person.getAttribute() == proposerType) {
				proposers.add(person);
			}
			else if (person.getAttribute() == proposeeType) {
				proposees.add(person);
			}
			else {
				throw new IllegalArgumentException("List of candidates has more than two groups.");
			}
		}
	}

	private void validateSetUpGroupsInputs(List<Person> people, Attributes proposerType, Attributes proposeeType) {

		if (people.isEmpty()) {
			throw new IllegalArgumentException("Empty list of people.");
		}
		
		if (proposerType == proposeeType) {
			throw new IllegalArgumentException("Proposers and Proposees shouldn't be the same.");
		}
	}

	
	protected List<Person> proposers = new ArrayList<>();
	protected List<Person> proposees = new ArrayList<>();

	
	/*
	 * for logging
	 */
	static Logger log = Logger.getLogger(MatchMaker.class.getName());
	
}
