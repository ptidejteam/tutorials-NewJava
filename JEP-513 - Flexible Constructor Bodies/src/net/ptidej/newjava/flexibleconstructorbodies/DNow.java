package net.ptidej.newjava.flexibleconstructorbodies;

class Object2 {
	Object2() {
		// Object constructor body
	}
}

class A2 extends Object {
	A2() {
		// A prologue
		super();
		// A epilogue
	}
}

class B2 extends A2 {
	B2() {
		// B prologue
		super();
		// B epilogue
	}
}

class C2 extends B2 {
	C2() {
		// C prologue
		super();
		// C epilogue
	}
}

public class DNow extends C2 {
	DNow() {
		// D prologue
		super();
		// D epilogue
	}
}