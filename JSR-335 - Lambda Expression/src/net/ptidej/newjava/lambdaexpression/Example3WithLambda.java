package net.ptidej.newjava.lambdaexpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Example3WithLambda {
	public static void main(final String[] args) {
		List<String> output;

		final List<String> friends1 = Arrays.asList("Rick Deckard", "Roy Batty", "Harry Bryant", "Hannibal Chew",
				"Gaff", "Holden", "Leon Kowalski", "Taffey Lewis", "Pris", "Rachael", "J.F. Sebastian",
				"Dr. Eldon Tyrell", "Zhora", "Hodge", "Mary");
		final List<String> friends2 = Arrays.asList("Paul Atreides", "Lady Jessica", "Piter De Vries",
				"Padishah Emperor Shaddam IV", "The Shadout Mapes", "Thufir Hawat", "Duncan Idaho", "Princess Irulan",
				"Stilgar", "Baron Vladimir Harkonnen", "Reverend Mother Gaius Helen Mohiam", "Duke Leto Atreides",
				"Gurney Halleck", "Feyd-Rautha", "Doctor Wellington Yueh", "Doctor Kynes", "Alia", "Chani");

		output = friends1.stream().filter(name -> name.startsWith("R")).collect(Collectors.toList());
		System.out.println(output);
		output = friends2.stream().filter(name -> name.startsWith("R")).collect(Collectors.toList());
		System.out.println(output);

		final Predicate<String> predicate = name -> name.startsWith("R");
		output = friends1.stream().filter(predicate).collect(Collectors.toList());
		System.out.println(output);
		output = friends2.stream().filter(predicate).collect(Collectors.toList());
		System.out.println(output);

		// Lexical scoping and closure
		output = friends1.stream().filter(checkIfStartsWith("R")).collect(Collectors.toList());
		System.out.println(output);
		output = friends2.stream().filter(checkIfStartsWith("P")).collect(Collectors.toList());
		System.out.println(output);

		// Narrower lexical scoping
		final Function<String, Predicate<String>> startsWithLetter = letter -> (name -> name.startsWith(letter));
		output = friends1.stream().filter(startsWithLetter.apply("R")).collect(Collectors.toList());
		System.out.println(output);
		output = friends2.stream().filter(startsWithLetter.apply("P")).collect(Collectors.toList());
		System.out.println(output);
	}

	public static Predicate<String> checkIfStartsWith(final String someLetters) {
		return name -> name.startsWith(someLetters);
	}
}
