package net.ptidej.newjava.lambdavar;

import org.eclipse.jdt.annotation.NonNull;

interface Comparator<T> {
	int compare(final T a, final T b);
}

public class Example1 {
	public static void main(final String[] args) {
		final Comparator<String> comparator1 = new Comparator<>() {
			@Override
			public int compare(final String a, final String b) {
				return a.compareTo(b);
			}
		};
		System.out.println(comparator1.compare("Hello", "World"));

		final Comparator<String> comparator2 = (a, b) -> a.compareTo(b);
		System.out.println(comparator2.compare("Hello", "World"));

		final Comparator<String> comparator3 = (String a, String b) -> a.compareTo(b);
		System.out.println(comparator3.compare("Hello", "World"));

		final Comparator<String> comparator4 = (final var a, final var b) -> a.compareTo(b);
		System.out.println(comparator4.compare("Hello", "World"));

		final Comparator<String> comparator5 = (@NonNull var a, @NonNull var b) -> a.compareTo(b);
		System.out.println(comparator5.compare("Hello", "World"));
	}
}
