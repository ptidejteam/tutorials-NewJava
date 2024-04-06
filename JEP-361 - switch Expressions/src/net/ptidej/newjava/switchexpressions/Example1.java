// https://medium.com/@imagarg/switch-expression-jep-361-3b5649ec36c9
package net.ptidej.newjava.switchexpressions;

public class Example1 {
	enum Day {
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	}

	private static int withSwitchStatement(final Day day) {
		int numberOfCharacters = 0;
		switch (day) {
		case MONDAY:
		case FRIDAY:
		case SUNDAY:
			numberOfCharacters = 6;
			break;
		case TUESDAY:
			numberOfCharacters = 7;
			break;
		case THURSDAY:
		case SATURDAY:
			numberOfCharacters = 8;
			break;
		case WEDNESDAY:
			numberOfCharacters = 9;
			break;
		default:
			throw new IllegalArgumentException();
		}
		return numberOfCharacters;
	}

	private static int withSwitchExpression(final Day day) {
		int result = switch (day) {
		case MONDAY, FRIDAY, SUNDAY -> 6;
		case TUESDAY -> 7;
		case THURSDAY, SATURDAY -> 8;
		case WEDNESDAY -> 9;
		default -> throw new IllegalArgumentException();
		};
		return result;
	}

	public static void main(final String[] args) {
		System.out.println(withSwitchExpression(Day.MONDAY));
		System.out.println(withSwitchStatement(Day.MONDAY));
	}
}
