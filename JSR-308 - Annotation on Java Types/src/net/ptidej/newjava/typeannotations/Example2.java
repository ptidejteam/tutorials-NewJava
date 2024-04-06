package net.ptidej.newjava.typeannotations;

import org.eclipse.jdt.annotation.NonNull;

class A {
	String getString() {
		return null;
	}
}

public class Example2 {
	public static void foo1() {
		final A a = new A();
		final String aString = a.getString();
		final @NonNull String anotherString = aString;
		System.out.println(anotherString);
	}

	public static void foo2() {
		final A a = new A();
		final String aString = a.getString();
		if (aString != null) {
			final @NonNull String anotherString = aString;
			System.out.println(anotherString);
		}
	}
}
