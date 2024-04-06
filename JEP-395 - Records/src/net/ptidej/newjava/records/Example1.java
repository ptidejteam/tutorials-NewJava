// https://www.baeldung.com/java-record-keyword
package net.ptidej.newjava.records;

import java.lang.reflect.Field;

record Person(String firstName, String lastName) {
}

public class Example1 {
	public static void main(final String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		final Person person1 = new Person("Rick", "Deckard");
		System.out.println(person1);

		final Person person2 = new Person("Rick", "Deckard");
		System.out.println(person2);

		final Person person3 = new Person("Roy", "Batty");
		System.out.println(person3);

		System.out.print(person1.firstName());
		System.out.println(person1.equals(person2));
		System.out.println(person1.equals(person3));

		final Class<?> personClass = Class.forName("net.ptidej.newjava.records.Person");
		final Field firstNameField = personClass.getDeclaredField("firstName");
		firstNameField.set(person1, "Leon");
	}
}
