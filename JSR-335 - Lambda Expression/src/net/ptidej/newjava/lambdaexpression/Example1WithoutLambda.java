package net.ptidej.newjava.lambdaexpression;

public class Example1WithoutLambda {
	interface Applicable<T, R> {
		public R apply(final T aParameter);
	}

	public static void main(final String[] args) {
		final Applicable<String, Integer> strlen = new Applicable<>() {
			@Override
			public Integer apply(final String aParameter) {
				return aParameter.length();
			}
		};

		System.out.println(strlen.apply("Hello, World!"));
	}
}
