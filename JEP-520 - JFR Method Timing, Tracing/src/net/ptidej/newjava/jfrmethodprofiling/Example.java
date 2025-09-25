// From https://openjdk.org/jeps/515

package net.ptidej.newjava.jfrmethodprofiling;

import java.util.List;
import java.util.stream.Collectors;

public class Example {
	@Debug
	static String greeting(final int n) {
		final var words = List.of("Hello", "" + n, "world!");
		return words.stream().filter(w -> !w.contains("0"))
				.collect(Collectors.joining(", "));
	}

	public static void main(final String... args) {
		for (int i = 0; i < 100_000; i++) {
			Example.greeting(i);
		}
		System.out.println(Example.greeting(0)); // "Hello, world!"
	}
}