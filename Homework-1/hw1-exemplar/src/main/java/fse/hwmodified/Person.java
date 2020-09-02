package fse.hwmodified;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a person to be matched as a POJO.  Has four properties:
 * 
 * 		name - String that represents the name of the Person
 * 		attribute - an Attributes that represents the class the Person belongs to
 * 		preferences - List<String> that represents this Person's preferred choices for being matched
 * 		match - Person that points to whom this Person is matched.
 * 
 * @author micha
 * @version 1.0  9/21/2019
 *
 */
public class Person implements Comparable<Person> {
	
	/*
	 * Builder pattern for constructing a Person
	 * 
	 * Requires name to be set to build a Person.
	 * 
	 */
	public static class Builder {
		
		public Builder(String name) {
			this.name = name;
		}
		
		public Builder setAttribute(Attributes attribute) {
			this.attribute = attribute;
			
			return this;
		}
		
		public Builder setPreferences(List<String> preferences) {
			this.preferences = preferences;
			
			return this;
		}
		
		/* 
		 * Construct a Person per the builder pattern
		 */
		
		public Person build() {
			
			Person p = new Person();
			
			p.setName(name);
			 
			if (preferences == null) {
				preferences = Arrays.asList();
			}
			
			p.setPreferences(preferences);
			p.setAttribute(attribute);
			p.match = null;
			
			return p;
		}
		
		private String name;
		private Attributes attribute;
		private List<String> preferences;
		
	}
	
	/*
	 * per the builder pattern, setting the constructor private
	 */
	
	private Person() {
		
	}

	public Attributes getAttribute() {
		return attribute;
	}
	
	public void setAttribute(Attributes attribute) {
		
		this.attribute = attribute;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		/* the uniqueness can be solved using a factory */
		
		this.name = name;
	}
	
	public List<String> getPreferences() {
		
		return  preferenceList.stream().collect(Collectors.toList());
	}
	
	public void setPreferences(List<String> preferences) {
		
		preferenceList = new ArrayList<>(preferences);
	}
	
	public Person getMatch() {
		return match;
	}
	
	public void setMatch(Person p) {
		
		this.match = p;
	}
	
	public void removePreference(String choice) {
		
		preferenceList.remove(choice);
	}
	
	public void removePreference(Person choice) {
		
		preferenceList.remove(choice.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(attribute, match, name, preferenceList);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Person))
			   return false;

		/** 
		 * replaces the traditional
		 
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		*/
		
		Person other = (Person) obj;
		
		return attribute == other.attribute && Objects.equals(match, other.match) && Objects.equals(name, other.name)
				&& Objects.equals(preferenceList, other.preferenceList);
	}
	
	
	
	@Override
    public int compareTo(Person another) {

		return name.compareTo(another.getName());
    }
	
	private List<String> preferenceList;
	private Attributes attribute;
	private String name;
	private Person match;
	
	
}
