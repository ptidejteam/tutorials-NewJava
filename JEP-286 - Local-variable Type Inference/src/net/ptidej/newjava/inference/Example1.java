package net.ptidej.newjava.inference;

import java.util.ArrayList;
import java.util.List;

public class Example1 {
	public static void main(final String[] args) {
		final List<String> list1 = new ArrayList<>();
		System.out.println(list1);

		final var list2 = new ArrayList<String>();
		System.out.println(list2);
	}
}
