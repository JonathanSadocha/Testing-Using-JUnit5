import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;
import java.util.*;

class TriangleTest {
	
	@ParameterizedTest
	@MethodSource({"dataProviderForAllTestCases"})
	void testAll(int a, int b, int c, String expectedResult) throws InvalidValueException {
		if(expectedResult == "Out Of Range") {
			assertThrows(InvalidValueException.class,() -> Triangle.type(a, b, c));
		} else {
			assertEquals(expectedResult, Triangle.type(a, b, c));
		}
	}
	
	static Stream<Arguments> dataProviderForAllTestCases() {
		Integer[] values = {0, 1, 2, 100, 199, 200, 201};
		
		ArrayList<Arguments> argsList = new ArrayList<Arguments>();
		String expectedResult;
		for(int a:values){  
			for(int b:values) {
				for(int c:values) {
					if (!(1 <= a && a <= 200) || !(1 <= b && b <= 200) || !(1 <= c && c <= 200)) {
						expectedResult = "Out Of Range";
					} else if (!(a < b + c) || !(b < a + c) || !(c < a + b)) {
						expectedResult = "NotATriangle";
					} else if (a == b && b == c) {
						expectedResult = "Equilateral";
					} else if (a != b && a != c && b != c) {
						expectedResult = "Scalene";
					} else {
						expectedResult = "Isosceles";
					}
					argsList.add(arguments(a,b,c,expectedResult));
				}
			}
		}
		
		Arguments[] args = argsList.toArray(new Arguments[argsList.size()]);
		return Stream.of(args);
	}
	
}
