package matching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class to stable match proposers to proposees based on defined attributes using the Gale
 * Shapley algorithm.
 */
public class MatchMaker {
	private List<Person> proposers = new ArrayList<>();
	private List<Person> proposees = new ArrayList<>();

	/**
	 * Function to match proposers to proposees. Cycles through entire proposer list to maximum
	 * proposers are matched to proposees. Uneven lists have proposees or proposers that are not
	 * matched.
	 */
	public void makeMatches() {
		while (unmatched()) {
			for (Person proposer : proposers) {
				if(proposer.getMatch() == null) {
					Person otherPerson = findProposee(proposer.dequePreference());
					if (otherPerson != null) {
						determineMatch(proposer, otherPerson);
					}
				}
			}
		}
	}

	/**
	 * Determines if the proposer and proposee are a match. A match means that the proproser and
	 * proposee are both unmatched, or that the proposee prefers the proposer over their current
	 * match.
	 *
	 * @param proposer person proposing.
	 * @param otherPerson proposee.
	 */
	private void determineMatch(Person proposer, Person otherPerson) {
		if (otherPerson.getMatch() != null) {
			Person otherPartner = otherPerson.getMatch();
			if (otherPerson.getRank(proposer) < otherPerson.getRank(otherPartner)) {
				otherPartner.setMatch(null);
				matchPeople(proposer, otherPerson);
			}
		} else {
			matchPeople(proposer, otherPerson);
		}
	}

	/**
	 * Determine if there is still a free proposer that is not matched with a proposee
	 *
	 * @return true if the proposer is unmatched and false otherwise.
	 */
	private boolean unmatched() {
		for (Person proposersFree : proposers) {
			if (proposersFree.getMatch() == null && !(proposersFree.getPreferences().isEmpty())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get relevant interest.
	 *
	 * @param interest the proposers interest
	 * @return the proposees interest
	 */
	private Person findProposee(String interest) {
		return proposees.stream()
						.filter(p -> p.getName().equals(interest))
						.findAny()
						.orElse(null);
	}

	/**
	 * Match the couple.
	 *
	 * @param proposer Person proposing.
	 * @param otherPerson Proposee.
	 */
	private void matchPeople(Person proposer, Person otherPerson){
		otherPerson.setMatch(proposer);
		proposer.setMatch(otherPerson);
	}

	/**
	 * Takes a list of people and sorts them into separate lists based on their attributes.
	 *
	 * @param people list of people
	 * @param proposerType attribute for the proposer.
	 * @param proposeeType attribute for the proposee.
	 * @throws IllegalArgumentException if the lists are empty or the attributes are the same.
	 */
	public void setUpGroups(List<Person> people, Attributes proposerType, Attributes proposeeType) {
		if (people.isEmpty()) {
			throw new IllegalArgumentException("Empty list of people.");
		}
		if (proposerType == proposeeType) {
			throw new IllegalArgumentException("Proposers and Proposees shouldn't be the same.");
		}

		for (Person person : people) {
			sortPerson(person, proposerType, proposeeType);
		}
	}

	/**
	 * Sorts the person to the appropriate group based on attribute.
	 * @param person person.
	 * @param proposerType proposer attribute.
	 * @param proposeeType proposee attribute.
	 * @throws IllegalArgumentException if there is a third attribute.
	 */
	private void sortPerson(Person person, Attributes proposerType, Attributes proposeeType) {
		if (person.getAttribute() == proposerType) {
			proposers.add(person);
		}
		else if (person.getAttribute() == proposeeType) {
			proposees.add(person);
		}
		else {
			throw new IllegalArgumentException("List of candidates has more than two groups.\n");
		}
	}

	/**
	 * Getter for the list
	 *
	 * @param proposal the proposal types.
	 * @return list of persons matching the proposal.
	 */
	public List<Person> getList(String proposal) {
		if (!proposal.contentEquals("proposers") && !proposal.contentEquals("proposees")) {
			throw new IllegalArgumentException("Must be proposers or proposees - not " + proposal);
		}

		if (proposal.contentEquals("proposers")) {
			return proposers;
		}
		else if (proposal.contentEquals("proposees")) {
			return proposees;
		}
		else
			return Collections.emptyList();
	}

	public void report() throws IOException {
		String className = MatchMaker.class.getName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		Logger logger = Logger.getLogger(className);

		FileHandler handler;
		try {
			handler = new FileHandler("matchmaker.txt");
		} catch (SecurityException | IOException e) {
			throw new IOException("Could not create file.");
		}
		handler.setFormatter(new SimpleFormatter());
		logger.addHandler(handler);
		logger.entering(className, method);

		for (Person ps: proposers) {
			if(ps.getMatch() != null) {
				logger.log(Level.INFO, ps.getName() + " matched with " + ps.getMatch().getName());
			} else {
				logger.log(Level.INFO, ps.getName() + " is not matched.");
			}
		}

		for (Person pp: proposees) {
			if (pp.getMatch() == null) {
				logger.log(Level.INFO, pp.getName() + " is not matched.");
			}
		}
	}
}
