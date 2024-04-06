package net.ptidej.newjava.deserialization;

import java.io.ObjectInputFilter;

public class Example1 {
	public static void main(final String[] args) {
		final ObjectInputFilter filter = ObjectInputFilter.Config.createFilter("net.ptidej.newjava.*;!*");
		ObjectInputFilter.Config.setSerialFilter(filter);
	}
}
