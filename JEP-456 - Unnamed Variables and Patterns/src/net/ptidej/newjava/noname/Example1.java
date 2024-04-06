package net.ptidej.newjava.noname;

import java.util.ArrayList;
import java.util.List;

class Order {
}

public class Example1 {
	public static void main(final String[] args) {
		final List<Order> orders = new ArrayList<>();
		int total = 0;
		for (final Order _ : orders) {
			total++;
		}
		System.out.println(total);
	}
}