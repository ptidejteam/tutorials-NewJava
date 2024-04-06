package net.ptidej.newjava.lambdaexpression;

public class Example1WithLambda {
	interface Applicable<T, R> {
		public R apply(final T aParameter);
	}

	public static void main(final String[] args) {
		final Applicable<String, Integer> strlen = String::length;
		System.out.println(strlen.apply("Hello, World!"));
	}
}
