import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;
import java.util.*;

class DateTest {

	@ParameterizedTest
	@MethodSource({"dataProviderForAllTestCases"})
	void testAll(int month, int day, int year, String expectedResult)
											throws InvalidValueException, InvalidDateException {
		
		if (expectedResult == "InvalidValueException") {
			assertThrows(InvalidValueException.class,() -> Date.nextDate(month, day, year));
		}else if (expectedResult == "InvalidDateException") {
			assertThrows(InvalidDateException.class,() -> Date.nextDate(month, day, year));
		}else {
		assertEquals(expectedResult, Date.nextDate(month, day, year));
		}
	}
	
	static Stream<Arguments> dataProviderForAllTestCases() {
		int[] months = {0, 1, 2, 6, 11, 12, 13};
		int[] days = {0, 1, 2, 15, 30, 31, 32};
		int[] years = {1811, 1812, 1813, 1912, 2011, 2012, 2013};
		
		ArrayList<Arguments> argsList = new ArrayList<Arguments>();
		
		for(int m:months){  
			for(int d:days) {
				for(int y:years) {
					String expectedResult = expectedOutput(m,d,y);
					
					argsList.add(arguments(m,d,y,expectedResult));
				}
			}
		}
		Arguments[] args = argsList.toArray(new Arguments[argsList.size()]);
		
		return Stream.of(args);
	}
	
	private static String expectedOutput(int month, int day, int year) {
		if (month < 1 || month > 12 || day < 1 || day > 31 || year < 1812 || year > 2012) {
			return "InvalidValueException";
		}
		try {
			GregorianCalendar g = new GregorianCalendar();
			g.setLenient(false);
			
			
			g.set(year, month-1, day);
			g.add(GregorianCalendar.DATE, 1);
			
			int tomorrowDay = g.get(GregorianCalendar.DATE),
					tomorrowMonth = g.get(GregorianCalendar.MONTH)+1,
					tomorrowYear = g.get(GregorianCalendar.YEAR);
			
			String MMDDYYYY = String.format("%02d/%02d/%04d", tomorrowMonth, tomorrowDay, tomorrowYear);;
			//
			// TO DO:
			//		use set() and add() methods of GregorianCalendar class to
			//		the set the date information and then advance it by 24 hours
			//		to go to next date
			//
			//		construct string representation of object g in format "MM/DD/YYYY"
			//
			
			return MMDDYYYY;	// replace this with the string constructed above
			
		} catch (IllegalArgumentException ex) {
			return "InvalidDateException";
		}
	}

}
