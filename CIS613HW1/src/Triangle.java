public class Triangle {

	public static String type(int a, int b, int c) throws InvalidValueException {
		if (!isInRange(a,b,c)) {
			throw new InvalidValueException("Value of one or more sides is not in the range of permitted values");
		}
		if (!isTriangle(a,b,c)) {
			return "NotATriangle";
		}
		if (a == b && b == c) {
			return "Equilateral";
		} else if (a != b && a != c && b != c) {
			return "Scalene";
		} else {
			return "Isosceles";
		}
	}
	
	private static boolean isInRange(int a, int b, int c) {
		boolean c1 = 1 <= a && a <= 200;
		boolean c2 = 1 <= b && b <= 200;
		boolean c3 = 1 <= c && c <= 200;
		return c1 && c2 && c3;
	}
	
	private static boolean isTriangle(int a, int b, int c) {
		return (a < b + c) && (b < a + c) && (c < a + b);
	}
	
}
