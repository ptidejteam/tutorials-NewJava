package net.ptidej.newjava.flexibleconstructorbodies;

class X {

	int i;
	String s = "hello";

	X() {
		System.out.print(this); // Error - explicitly refers to the current instance

		var x = this.i; // Error - explicitly refers to field of the current instance
		this.hashCode(); // Error - explicitly refers to method of the current instance

		var y = i; // Error - implicitly refers to field of the current instance
		hashCode(); // Error - implicitly refers to method of the current instance

		i = 42; // OK - assignment to an uninitialized declared field

		s = "goodbye"; // Error - assignment to an initialized declared field

		super();
	}
}