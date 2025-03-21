/*
 * https://openjdk.org/jeps/485
 */
package net.ptidej.newjava.gatherers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExamplesWithoutGatherers {

	public static void main(final String[] args) {
		ExamplesWithoutGatherers.typicalUsage();
		ExamplesWithoutGatherers.statefulUsage1();
		ExamplesWithoutGatherers.statefulUsage2();
		ExamplesWithoutGatherers.signalAndParallelismUsage();
	}

	public static void typicalUsage() {
		final long numberOfWords = Stream
				.of("the", "", "fox", "jumps", "over", "the", "", "dog")
				.filter(Predicate.not(String::isEmpty))
				.collect(Collectors.counting());
		System.out.println(numberOfWords);
	}

	public static void statefulUsage1() {
		final List<String> result = Stream
				.of("foo", "bar", "bar", "bar", "zorg").distinct().toList();
		System.out.println(result);
	}

	public static void statefulUsage2() {
		record DistinctByLength(String str) {
			@Override
			public boolean equals(final Object obj) {
				return obj instanceof DistinctByLength(final String other)
						&& str.length() == other.length();
			}

			@Override
			public int hashCode() {
				return str == null ? 0 : Integer.hashCode(str.length());
			}
		}

		final List<String> result = Stream
				.of("foo", "bar", "bar", "bar", "zorg")
				.map(DistinctByLength::new).distinct()
				.map(DistinctByLength::str).toList();
		System.out.println(result);
	}

	public static void signalAndParallelismUsage() {
		final ArrayList<ArrayList<Integer>> result = Stream
				.iterate(0, i -> i + 1).limit(3 * 2)
				.collect(Collector.of(() -> new ArrayList<ArrayList<Integer>>(),
						(groups, element) -> {
							if (groups.isEmpty()
									|| groups.getLast().size() == 3) {
								var current = new ArrayList<Integer>();
								current.add(element);
								groups.addLast(current);
							}
							else {
								groups.getLast().add(element);
							}
						}, (_, _) -> {
							throw new UnsupportedOperationException(
									"Cannot be parallelized");
						}));
		System.out.println(result);
	}

}
