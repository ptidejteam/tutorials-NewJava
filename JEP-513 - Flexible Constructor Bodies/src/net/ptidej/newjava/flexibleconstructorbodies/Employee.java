// From https://openjdk.org/jeps/513

package net.ptidej.newjava.flexibleconstructorbodies;

class Employee extends Person {
	private final String officeID;

	Employee(int age, String officeID) {
		if (age < 18 || age > 67) {
			// Now fails fast!
			throw new IllegalArgumentException("...");
		}
		this.officeID = officeID; // Initialize before calling superclass constructor!
		super(age);
	}
}
