/**
 * 
 */
package fse.hwmodified;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * This class is a decorator that adds Logging Brackets to each method call.
 * 
 * @author micha
 *
 */
public class LoggingMatchMaker implements IMatchMaker {

	private final IMatchMaker classToBeLogged;
	
	public LoggingMatchMaker (IMatchMaker classToBeLogged) {
        this.classToBeLogged = classToBeLogged;
        className = classToBeLogged.getClass().getName();
    }
	
	@Override
	public void makeMatches() {
		
		bracketMessage(ENTERING, Thread.currentThread().getStackTrace()[2].getMethodName());
		
		classToBeLogged.makeMatches();
		
		bracketMessage(LEAVING, Thread.currentThread().getStackTrace()[2].getMethodName());
	}

	private void bracketMessage(String bracket, String methodName) {
		log.debug(bracket + this.className + "." + methodName);
	}

	@Override
	public void setUpGroups(List<Person> people, Attributes proposerType, Attributes proposeeType) {
		
		bracketMessage(ENTERING, Thread.currentThread().getStackTrace()[2].getMethodName());
		
		classToBeLogged.setUpGroups(people, proposerType, proposeeType);
		
		bracketMessage(LEAVING, Thread.currentThread().getStackTrace()[2].getMethodName());

	}

	@Override
	public List<Person> getList(String groupName) {
		
		bracketMessage(ENTERING, Thread.currentThread().getStackTrace()[2].getMethodName());
		
		List<Person> result = classToBeLogged.getList(groupName);
		
		bracketMessage(LEAVING, Thread.currentThread().getStackTrace()[2].getMethodName());
		
		return result;
		
	}

	
	static Logger log = Logger.getLogger(IMatchMaker.class.getName());
	private String className;
	private static final String ENTERING = "Entering ";
	private static final String LEAVING = "Leaving ";
}
