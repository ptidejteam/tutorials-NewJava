package net.ptidej.newjava.switchpatternmatching;

record Person(String firstName, String lastName) {
}

public class Example1 {
	public static void main(final String[] args) {
		final String response = "Yes";

		switch (response) {
		case null -> {
			System.out.println("Boom!");
		}
		case String s when s.equalsIgnoreCase("YES") -> {
			System.out.println("You got it");
		}
		case String s when s.equalsIgnoreCase("NO") -> {
			System.out.println("Too bad");
		}
		case String s -> {
			System.out.println("Sorry?");
		}
		}
	}
}
