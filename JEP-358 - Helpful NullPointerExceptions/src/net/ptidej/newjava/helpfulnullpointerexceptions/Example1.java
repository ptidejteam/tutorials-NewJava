package net.ptidej.newjava.helpfulnullpointerexceptions;

class Employee {
	String getName() {
		// return "Bob";
		return null;
	}
}

public class Example1 {
	public static void main(final String[] args) {
		final Employee e = new Employee();
		e.getName().toString();
	}
}
