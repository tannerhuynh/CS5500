package matching;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a person implementing the Comparable class.
 * A person has a name, attribute, list of preferences, and who they are matched with.
 */
public class Person implements Comparable<Person> {
	private List<String> preferenceList;
	private Attributes attribute;
	private String name;
	private Person match;

	/**
	 * Constructor for a person.
	 *
	 * @param name string representing the name of the person.
	 * @param attribute enum representing the person's attribute (for example gender).
	 * @param preferences list of preferences of other people this person wants to match.
	 */
	public Person(String name, Attributes attribute, List<String> preferences) {
		setName(name);
		setAttribute(attribute);
		setPreferences(preferences);
	}

	/**
	 * Empty constructor for a person.
	 */
	public Person() {
	}

	/**
	 * Getter for the attribute of the person.
	 *
	 * @return person's attribute.
	 */
	public Attributes getAttribute() {
		return attribute;
	}

	/**
	 * Setter for the attribute of the person.
	 *
	 * @param attribute sets the attribute for this person.
	 */
	public void setAttribute(Attributes attribute) {
		this.attribute = attribute;
	}

	/**
	 * Getter for the name of the person.
	 *
	 * @return the name of the person.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of the person.
	 *
	 * @param name sets the name for this person.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the list of preferences for the person.
	 *
	 * @return list of preferences for this person.
	 */
	public List<String> getPreferences() {
		return preferenceList;
	}


	/**
	 * Get the initial preference from the Person's preference list and remove it so it won't be
	 * used again.
	 *
	 * @return the String of the initial preference of this person.
	 */
	public String dequePreference() {
		String interest = this.getPreferences().get(0);
		this.getPreferences().remove(interest);
		return interest;
	}

	/**
	 * Setter for the list of preferences for the person.
	 *
	 * @param preferences sets the list of preferences for this person.
	 */
	public void setPreferences(List<String> preferences) {
		preferenceList = new ArrayList<>(preferences);
	}

	/**
	 * Getter to get the match for the person.
	 *
	 * @return the person that this person is matched with otherwise null.
	 */
	public Person getMatch() {
		return match;
	}

	/**
	 * Getter for the ranking of the given person in the current persons's preference list.
	 *
	 * @param p Person of interest.
	 * @return the ranking of the person of interest.
	 */
	public int getRank(Person p) {
		return preferenceList.indexOf(p.toString());
	}

	/**
	 * Setter for the match for the person.
	 *
	 * @param p the person to be matched with this person.
	 */
	public void setMatch(Person p) {
		this.match = p;
	}
	
	@Override
	public boolean equals(Object another) {
		if  (!(another instanceof Person)) {
			return false;
		}

		Person other = (Person) another;
		return getName().contentEquals(other.getName())
						&& (getAttribute() == other.getAttribute())
						&& (getMatch() == other.getMatch())
						&& (getPreferences().equals(other.getPreferences()));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
    public int compareTo(Person another) {
		return name.compareTo(another.getName());
    }
}
