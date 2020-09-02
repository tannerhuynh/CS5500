package fse.hwmodifiedtest;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fse.hwmodified.Attributes;
import fse.hwmodified.IMatchMaker;
import fse.hwmodified.Person;
import fse.hwmodified.StableMatchMaker;

public class TestMatch {

	
	@Before
	public void setUp() throws Exception {
		
		p1 = makePerson("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		p2 = makePerson("Frasier",Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla", "Kelly"));
		p3 = makePerson("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane", "Kelly"));
		p4 = makePerson("Woody", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		
		p5 = makePerson("Diane", Attributes.FEMALE, Arrays.asList("Sam", "Frasier", "Coach", "Woody"));
		p6 = makePerson("Carla", Attributes.FEMALE, Arrays.asList("Coach", "Woody", "Sam", "Frasier"));
		p7 = makePerson("Lilith", Attributes.FEMALE, Arrays.asList("Frasier", "Sam", "Woody", "Coach"));
		p8 = makePerson("Kelly", Attributes.FEMALE, Arrays.asList("Woody", "Sam", "Coach", "Frasier"));
		
		everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
	}

	@After
	public void tearDown() throws Exception {
		
		p1 = null;
		p2 = null;
		p3 = null;
		p4 = null;
		p5 = null;
		p6 = null;
		p7 = null;
		p8 = null;
		
		everyone = null;
	}

	@Test
	public void testMakeMatches() {

		IMatchMaker m = new StableMatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		
		for (Person p : everyone) {
			
			System.out.println(p.getName() + " is matched with " + p.getMatch().getName());
		}
		
		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p4.getMatch().getName(), is(p8.getName()));

	}

	@Test
	public void testMakeMatchesWithConflict() {
		
		p2.setPreferences(Arrays.asList("Diane", "Lilith", "Carla", "Kelly"));

		everyone = Arrays.asList(p2, p1, p3, p4, p5, p6, p7, p8);
		
		IMatchMaker m = new StableMatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		
		for (Person p : everyone) {
			
			System.out.println(p.getName() + " is matched with " + p.getMatch().getName());
		}
		
		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p4.getMatch().getName(), is(p8.getName()));

	}
	
	@Test
	public void testMakeMatchesTooFewGroupA() {
		
		List<Person> almostEveryone = Arrays.asList(p1, p2, p3, p5, p6, p7, p8);
		
		IMatchMaker m = new StableMatchMaker();
		
		m.setUpGroups(almostEveryone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		
		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p8.getMatch(), is(nullValue()));
		
	}
	
	@Test
	public void testMakeMatchesTooFewGroupB() {
		
		
		List<Person> almostEveryone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);
		
		IMatchMaker m = new StableMatchMaker();
		
		m.setUpGroups(almostEveryone, Attributes.MALE, Attributes.FEMALE);
		
		m.makeMatches();
		
		assertThat(p1.getMatch(), is(p5));
		assertThat(p2.getMatch(), is(p7));
		assertThat(p3.getMatch(), is(p6));
		assertThat(p4.getMatch(), is(nullValue()));
		
	}
	
	@Test
	public void testSetUpGroups() {
		
		IMatchMaker m = new StableMatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		List<Person> answer1 = Arrays.asList(p1, p2, p3, p4);
		List<Person> answer2 = Arrays.asList(p5, p6, p7, p8);
		
		assertThat(m.getList("groupA"), IsIterableContainingInOrder.contains(answer1.toArray()));
		assertThat(m.getList("groupB"), IsIterableContainingInOrder.contains(answer2.toArray()));

	}

	private Person makePerson(String name, Attributes attribute, List<String> preference) {
		
		return new Person.Builder(name).setAttribute(attribute).setPreferences(preference).build();
	}
	
	private Person p1, p2, p3, p4;
	private Person p5, p6, p7, p8;
	List<Person> everyone;
}
