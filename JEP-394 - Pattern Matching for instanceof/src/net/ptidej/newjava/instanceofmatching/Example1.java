package net.ptidej.newjava.instanceofmatching;

public class Example1 {
	public static void main(final String[] args) {
		final Object o1 = Integer.valueOf(1);
		if (o1 instanceof Integer) {
			final Integer integer = (Integer) o1;
			System.out.println(integer.intValue());
		}

		final Object o2 = String.valueOf(2);
		if (o2 instanceof Integer) {
			final Integer integer = (Integer) o2;
			System.out.println(integer.intValue());
		}

		final Object o3 = Integer.valueOf(3);
		if (o3 instanceof Integer integer) {
			System.out.println(integer.intValue());
		}

		final Object o4 = String.valueOf(4);
		if (o4 instanceof Integer integer) {
			System.out.println(integer.intValue());
		}

		final Object o5 = Integer.valueOf(5);
		if (o5 instanceof Integer integer && integer.intValue() < 11) {
			System.out.println(integer.intValue());
		}

		final Object o6 = Integer.valueOf(6);
		if (o6 instanceof final Integer integer) {
			// integer = Integer.valueOf(24);
			System.out.println(integer.intValue());
		}
	}

	private String s;

	public final boolean equals1(final Object o) {
		return (o instanceof String) && ((String) o).equalsIgnoreCase(s);
	}

	public final boolean equals2(final Object o) {
		return (o instanceof String s1) && s1.equalsIgnoreCase(s);
	}
}
