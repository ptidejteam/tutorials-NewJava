package net.ptidej.newjava.dataandtime;

import java.time.LocalTime;

public class Example1 {
	public static void main(final String[] args) {
		var now = LocalTime.now();
		System.out.println(now);
		
		var time = LocalTime.of(12, 45);
		System.out.println(time);
	}
}
