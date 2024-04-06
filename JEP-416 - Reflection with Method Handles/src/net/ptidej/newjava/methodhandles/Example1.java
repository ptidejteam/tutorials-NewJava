// https://blogs.oracle.com/javamagazine/post/java-reflection-method-handles
package net.ptidej.newjava.methodhandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Example1 {
	int compare(final String str1, final String str2) {
		return 42;
	}

	int compare(final String str, final int i) {
		return 24;
	}

	public static void main(final String[] args) throws Throwable {
		var lookup = MethodHandles.lookup();

		var methodType1 = MethodType.methodType(int.class, String.class, String.class);
		System.out.println("MethodType  : " + methodType1);
		var methodHandle1 = lookup.findVirtual(lookup.lookupClass(), "compare", methodType1);
		System.out.println("MethodHandle: " + methodHandle1.type());
		System.out.println(methodHandle1.invoke(new Example1(), "Hello", "World!"));

		var methodType2 = MethodType.methodType(int.class, String.class, int.class);
		System.out.println("MethodType  : " + methodType2);
		var methodHandle2 = lookup.findVirtual(lookup.lookupClass(), "compare", methodType2);
		System.out.println("MethodHandle: " + methodHandle2.type());
		System.out.println(methodHandle2.invoke(new Example1(), "Hello", 0));
	}
}
