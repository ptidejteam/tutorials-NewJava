package net.ptidej.modularisation.kernel.impl;

import net.ptidej.modularisation.kernel.Interface;

// Still shouldn't be public
class HiddenImplementation implements Interface {
	@Override
	public void foo() {
		this.bar();
	}

	private void bar() {
		System.out.println("HiddenImplementation.bar()");
	}
}
