package net.ptidej.newjava.typeannotations;

import org.eclipse.jdt.annotation.NonNull;

public class Example1 {
	public static void foo1() {
		final String aString = null;
		final String anotherString = aString;
		System.out.println(anotherString);
	}

	public static void foo2() {
		final String aString = null;
		final @NonNull String anotherString = aString;
		System.out.println(anotherString);
	}

	public static void foo3() {
		final String aString = "";
		final @NonNull String anotherString = aString;
		System.out.println(anotherString);
	}
}
