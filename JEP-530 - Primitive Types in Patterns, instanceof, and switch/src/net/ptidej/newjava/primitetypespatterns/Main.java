/*
 * https://openjdk.org/jeps/530
 */
package net.ptidej.newjava.primitetypespatterns;

public class Main {

	@SuppressWarnings("unused")
	public static void main(final String[] args) {

		int i = 0;
		if (i instanceof byte b) {
			System.out.println(b);
		}

		final LoyaltyCard loyaltyCard = new LoyaltyCard();
		final Person rachel = new Person("Rachel");
		switch (rachel.getYearlyFlights()) {
		case 0 -> System.out.println("0");
		case 1 -> System.out.println("1");
		case 2 -> loyaltyCard.issueDiscount();
		case int j when j >= 100 -> loyaltyCard.issueGoldCard();
		case int j -> System.out.println("2");
		}

		long v = 0L;
		switch (v) {
		case 1L -> System.out.println("A");
		case 2L -> System.out.println("B");
		case 10_000_000_000L -> System.out.println("C");
		case 20_000_000_000L -> System.out.println("D");
		case long x -> System.out.println("E");
		}

	}

}
