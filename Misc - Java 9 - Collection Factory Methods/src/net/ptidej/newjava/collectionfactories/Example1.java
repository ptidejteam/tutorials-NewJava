package net.ptidej.newjava.collectionfactories;

import java.util.List;

public class Example1 {
	public static void main(final String[] args) {
		final List<String> list = List.of("Rick Deckard", "Roy Batty", "Harry Bryant", "Hannibal Chew", "Gaff", "Holden",
				"Leon Kowalski", "Taffey Lewis", "Pris", "Rachael", "J.F. Sebastian", "Dr. Eldon Tyrell", "Zhora",
				"Hodge", "Mary");
		System.out.println(list);
		list.add("Paul Atreides");
	}
}
