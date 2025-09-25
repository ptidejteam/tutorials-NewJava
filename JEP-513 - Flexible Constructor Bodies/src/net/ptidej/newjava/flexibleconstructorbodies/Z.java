package net.ptidej.newjava.flexibleconstructorbodies;

class Z extends Y {
	Z() {
		var x = super.i; // Error
		super.m(); // Error
		super();
	}
}