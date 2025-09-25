package net.ptidej.newjava.recordpatterns;

record Person(String firstName, String lastName) {
}

public class Example1 {
	public static void main(final String[] args) {
		final Person person1 = new Person("Rick", "Deckard");
		final Person person2 = new Person("Roy", "Batty");

		final Object o1 = person1;
		if (o1 instanceof Person p) {
			System.out.println(p.firstName());
		}

		final Object o2 = person2;
		if (o2 instanceof Person(final String first, final String last)) {
			System.out.println(first);
		}
	}
}
