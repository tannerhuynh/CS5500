package matching;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMatch {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeMatches() throws IOException {
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla", "Kelly"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane", "Kelly"));
		Person p4 = new Person("Woody", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));

		Person p5 = new Person("Diane", Attributes.FEMALE, Arrays.asList("Sam", "Frasier", "Coach", "Woody"));
		Person p6 = new Person("Carla", Attributes.FEMALE, Arrays.asList("Sam", "Frasier", "Coach", "Woody"));
		Person p7 = new Person("Lilith", Attributes.FEMALE, Arrays.asList("Sam", "Frasier", "Coach", "Woody"));
		Person p8 = new Person("Kelly", Attributes.FEMALE, Arrays.asList("Sam", "Frasier", "Coach", "Woody"));

		Person a1 = new Person("Stew", Attributes.MALE, Arrays.asList("Lily",
						"Candy"));
		Person a2 = new Person("Filip", Attributes.MALE, Arrays.asList("Lily", "Doria", "Candy",
						"Madison"));
		Person a3 = new Person("Chris", Attributes.MALE, Arrays.asList("Doria", "Candy", "Lily"));
		Person a4 = new Person("William", Attributes.MALE, Arrays.asList("Lily", "Candy",
						"Doria"));

		Person a5 = new Person("Doria", Attributes.FEMALE, Arrays.asList("Stew", "Filip", "Chris",
						"William"));
		Person a6 = new Person("Candy", Attributes.FEMALE, Arrays.asList("Chris", "Stew", "Filip",
						"William"));
		Person a7 = new Person("Lily", Attributes.FEMALE, Arrays.asList("Stew", "Chris",
						"Filip"));
		Person a8 = new Person("Madison", Attributes.FEMALE, Arrays.asList("Filip", "Chris", "William",
						"Stew"));

		List<Person> everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
		List<Person> everyone2 = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8);

		MatchMaker m = new MatchMaker();
		MatchMaker n = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		n.setUpGroups(everyone2, Attributes.MALE, Attributes.FEMALE);

		m.makeMatches();
		n.makeMatches();

		try {
			m.report();
			n.report();
		} catch (SecurityException | IOException e) {
			throw new IOException("Could not create file.");
		}

		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p4.getMatch().getName(), is(p8.getName()));

		assertThat(a1.getMatch().getName(), is(a7.getName()));
		assertThat(a2.getMatch().getName(), is(a8.getName()));
		assertThat(a3.getMatch().getName(), is(a5.getName()));
		assertThat(a4.getMatch().getName(), is(a6.getName()));

	}

	@Test
	public void testMakeMatchesTooFewProposers() throws IOException {

		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach");
		
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane"));

		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		Person p8 = new Person("Kelly", Attributes.FEMALE, womensPreferences);
		
		List<Person> everyone = Arrays.asList(p1, p2, p3, p5, p6, p7, p8);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);

		m.makeMatches();

		try {
			m.report();
		} catch (SecurityException | IOException e) {
			throw new IOException("Could not create file.");
		}
    
		assertThat(p1.getMatch().getName(), is(p5.getName()));
		assertThat(p2.getMatch().getName(), is(p7.getName()));
		assertThat(p3.getMatch().getName(), is(p6.getName()));
		assertThat(p8.getMatch(), is(nullValue()));

	}
	
	@Test
	public void testMakeMatchesTooFewProposees() throws IOException {

		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach", "Woody");
		
		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane"));
		Person p4 = new Person("Woody", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith"));
		
		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		
		
		List<Person> everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);

		m.makeMatches();

		try {
			m.report();
		} catch (SecurityException | IOException e) {
			throw new IOException("Could not create file.");
		}
		
		assertThat(p1.getMatch(), is(p5));
		assertThat(p2.getMatch(), is(p7));
		assertThat(p3.getMatch(), is(p6));
		assertThat(p4.getMatch(), is(nullValue()));

	}
	
	@Test
	public void testSetUpGroups() {

		List<String> womensPreferences = Arrays.asList("Sam", "Frasier", "Coach", "Woody");

		Person p1 = new Person("Sam", Attributes.MALE, Arrays.asList("Diane", "Carla", "Lilith", "Kelly"));
		Person p2 = new Person("Frasier", Attributes.MALE, Arrays.asList("Lilith", "Diane", "Carla", "Kelly"));
		Person p3 = new Person("Coach", Attributes.MALE, Arrays.asList("Carla", "Lilith", "Diane", "Kelly"));
		Person p4 = new Person("Woody", Attributes.MALE, Arrays.asList("Kelly", "Diane", "Carla", "Lilith"));


		Person p5 = new Person("Diane", Attributes.FEMALE, womensPreferences);
		Person p6 = new Person("Carla", Attributes.FEMALE, womensPreferences);
		Person p7 = new Person("Lilith", Attributes.FEMALE, womensPreferences);
		Person p8 = new Person("Kelly", Attributes.FEMALE, womensPreferences);

		List<Person> everyone = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
		
		MatchMaker m = new MatchMaker();
		
		m.setUpGroups(everyone, Attributes.MALE, Attributes.FEMALE);
		
		List<Person> answer1 = Arrays.asList(p1, p2, p3, p4);
		List<Person> answer2 = Arrays.asList(p5, p6, p7, p8);
		
		assertThat(m.getList("proposers"), IsIterableContainingInOrder.contains(answer1.toArray()));
		assertThat(m.getList("proposees"), IsIterableContainingInOrder.contains(answer2.toArray()));

	}
}
