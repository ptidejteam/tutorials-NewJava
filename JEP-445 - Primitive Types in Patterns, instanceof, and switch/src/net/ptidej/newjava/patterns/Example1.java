package net.ptidej.newjava.patterns;

public class Example1 {
	public static void main(final String[] args) throws InterruptedException {
		final int someIntValue = 42;

		switch (someIntValue) {
		case 0 -> System.out.println("Got 0");
		case 1 -> System.out.println("Got 1");
		case 2 -> System.out.println("Got 2");
		case int i when i >= 100 -> System.out.println("Got " + i + " >= 100");
		case int i -> System.out.println("Got " + i + " >2 and <100");
		}
	}
}
