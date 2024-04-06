package net.ptidej.newjava.lambdaexpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Example2WithLambda {
	public static void main(final String[] args) {
		final List<String> friends = Arrays.asList("Rick Deckard", "Roy Batty", "Harry Bryant", "Hannibal Chew", "Gaff",
				"Holden", "Leon Kowalski", "Taffey Lewis", "Pris", "Rachael", "J.F. Sebastian", "Dr. Eldon Tyrell",
				"Zhora", "Hodge", "Mary");

		for (int i = 0; i < friends.size(); i++) {
			System.out.println(friends.get(i));
		}

		for (String name : friends) {
			System.out.println(name);
		}

		friends.forEach(new Consumer<String>() {
			public void accept(final String aName) {
				System.out.println(aName);
			}
		});

		friends.forEach((final String name) -> System.out.println(name));
		friends.forEach((name) -> System.out.println(name));
		friends.forEach(name -> System.out.println(name));
		friends.forEach(System.out::println);

		friends.stream().map(String::toUpperCase).forEach(name -> System.out.print(name + " "));
		System.out.println();

		final List<String> namesStartingWithN = friends.stream().filter(name -> name.startsWith("R"))
				.collect(Collectors.toList());
		System.out.println(namesStartingWithN);
	}
}
