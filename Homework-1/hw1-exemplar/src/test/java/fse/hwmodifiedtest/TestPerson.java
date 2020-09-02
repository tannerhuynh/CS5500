package fse.hwmodifiedtest;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fse.hwmodified.Attributes;
import fse.hwmodified.Person;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class TestPerson {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareTo() {
		
		String className = TestPerson.class.getName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		Logger LOGGER = Logger.getLogger(className);
		
		FileHandler handler;
		try {
			handler = new FileHandler("logs.txt");
		} catch (SecurityException | IOException e) {
			throw new RuntimeException("Woops.  Something burped on opening the log.");
		} 
		handler.setFormatter(new SimpleFormatter()); 

		LOGGER.addHandler(handler); 
		
		LOGGER.entering(className, method);
		
		Person p1 = new Person.Builder("Kanye").setAttribute(Attributes.MALE).build();
		
		Person p2 = new Person.Builder("Kim").setAttribute(Attributes.FEMALE).build();
		 
		LOGGER.log(Level.INFO, "Considering " + p1.getName() + " and " + p2.getName());

		assertThat(p1.compareTo(p2), lessThan(0));
		assertThat(p2.compareTo(p1), greaterThan(0));
		
		LOGGER.log(Level.INFO, "Done - passed.");
	}
	
	@Test 
	public void testRemovePreference() {
		
		Person p1 = new Person.Builder("Sam").setAttribute(Attributes.MALE).setPreferences(Arrays.asList("Diane", "Carla", "Lilith", "Kelly")).build();
			
		Person p5 =  new Person.Builder("Diane").setAttribute(Attributes.FEMALE).setPreferences(Arrays.asList("Sam", "Frasier", "Coach", "Woody")).build();
		
		p1.removePreference(p5);
		
		assertThat(p1.getPreferences(), contains("Carla", "Lilith", "Kelly"));
	}
}
