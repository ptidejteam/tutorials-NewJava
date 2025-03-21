/*
 * https://openjdk.org/jeps/485
 */
package net.ptidej.newjava.gatherers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Gatherer;
import java.util.stream.Gatherer.Downstream;
import java.util.stream.Gatherer.Integrator;
import java.util.stream.Gatherer.Integrator.Greedy;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class ExamplesWithGatherers {

	private record Reading(Instant obtainedAt, int kelvins) {
		Reading(String time, int kelvins) {
			this(Instant.parse(time), kelvins);
		}

		static Stream<Reading> loadRecentReadings() {
			// In reality these could be read from a file, a database, a service, or otherwise
			return Stream.of(new Reading("2023-09-21T10:15:30.00Z", 310),
					new Reading("2023-09-21T10:15:31.00Z", 312),
					new Reading("2023-09-21T10:15:32.00Z", 350),
					new Reading("2023-09-21T10:15:33.00Z", 310));
		}
	}

	public static void main(final String[] args) {
		ExamplesWithGatherers.simpleUsage1();
		ExamplesWithGatherers.simpleUsage2();
		System.out.println(ExamplesWithGatherers
				.findSuspiciousWithoutGatherer(Reading.loadRecentReadings()));
		System.out.println(ExamplesWithGatherers
				.findSuspiciousWithGatherer(Reading.loadRecentReadings()));
	}

	public static void simpleUsage1() {
		class DifferentLength implements Greedy<Void, String, String> {
			private Set<Integer> setOfLengths = new HashSet<>();

			public boolean integrate(final Void v, final String element,
					final Downstream<? super String> downstream) {

				final int length = element.length();
				if (!setOfLengths.contains(length)) {
					setOfLengths.add(length);
					return downstream.push(element);
				}
				else {
					return true;
				}
			}
		}

		final DifferentLength differentLength = new DifferentLength();
		final List<String> result = Stream
				.of("foo", "bar", "bar", "bar", "zorg")
				.gather(Gatherer.<String, String>ofSequential(Integrator
						.<Void, String, String>ofGreedy(differentLength)))
				.toList();
		System.out.println(result);
	}

	public static void simpleUsage2() {
		class DifferentLength {
			private final Set<Integer> setOfLengths;

			DifferentLength() {
				this.setOfLengths = new HashSet<>();
			}

			boolean integrate(final String element,
					final Downstream<? super String> downstream) {

				final int length = element.length();
				if (!setOfLengths.contains(length)) {
					setOfLengths.add(length);
					return downstream.push(element);
				}
				else {
					return true;
				}
			}

			void finish(final Downstream<? super String> downstream) {
			}
		}

		final List<String> result = Stream
				.of("foo", "bar", "bar", "bar", "zorg")
				.gather(Gatherer.<String, DifferentLength, String>ofSequential(
						DifferentLength::new,
						Integrator.<DifferentLength, String, String>ofGreedy(
								DifferentLength::integrate),
						DifferentLength::finish))
				.toList();
		System.out.println(result);
	}

	public static List<List<Reading>> findSuspiciousWithoutGatherer(
			final Stream<Reading> source) {

		final List<List<Reading>> suspicious = new ArrayList<List<Reading>>();

		Reading previous = null;
		boolean hasPrevious = false;
		for (Reading next : source.toList()) {
			if (!hasPrevious) {
				hasPrevious = true;
				previous = next;
			}
			else {
				if (isSuspicious(previous, next))
					suspicious.add(List.of(previous, next));
				previous = next;
			}
		}

		return suspicious;
	}

	public static List<List<Reading>> findSuspiciousWithGatherer(
			final Stream<Reading> source) {

		return source.gather(Gatherers.windowSliding(2))
				.filter(window -> (window.size() == 2
						&& isSuspicious(window.get(0), window.get(1))))
				.toList();
	}

	private static boolean isSuspicious(Reading previous, Reading next) {
		return next.obtainedAt().isBefore(previous.obtainedAt().plusSeconds(5))
				&& (next.kelvins() > previous.kelvins() + 30
						|| next.kelvins() < previous.kelvins() - 30);
	}
}
