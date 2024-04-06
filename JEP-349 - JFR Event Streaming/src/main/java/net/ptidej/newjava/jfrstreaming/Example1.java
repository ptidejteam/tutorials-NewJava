package net.ptidej.newjava.jfrstreaming;

import java.util.ArrayList;
import java.util.List;

public class Example1 {
	public static void main(final String[] args) {
		AgentMain.premain(null, null);

		// Some dummy workload
		final List<String> list = new ArrayList<>();
		for (int i = 0; i < 1_000_000; i++) {
			System.out.println(i);
			list.add("Hello World!");
		}
	}
}
