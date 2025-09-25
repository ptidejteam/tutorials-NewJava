package net.ptidej.newjava.flexibleconstructorbodies;

class Object1 {
	Object1() {
		// Object constructor body
	}
}

class A1 extends Object1 {
	A1() {
		super();
		// A constructor
	}
}

class B1 extends A1 {
	B1() {
		super();
		// B constructor
	}
}

class C1 extends B1 {
	C1() {
		super();
		// C constructor
	}
}

public class DBefore extends C1 {
	DBefore() {
		super();
		// D constructor
	}
}