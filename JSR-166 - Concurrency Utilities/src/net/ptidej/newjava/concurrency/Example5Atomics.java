// https://www.geeksforgeeks.org/atomic-variables-in-java-with-examples/
package net.ptidej.newjava.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class Counter extends Thread {
	private final AtomicInteger count;

	public Counter() {
		this.count = new AtomicInteger();
	}

	public void run() {
		final int max = 10_000_000;
		for (int i = 0; i < max; i++) {
			count.addAndGet(1);
		}
	}

	public int getCount() {
		return this.count.get();
	}
}

public class Example5Atomics {
	public static void main(final String[] args) throws InterruptedException {
		final Counter c = new Counter();

		// Defining Two different threads
		final Thread first = new Thread(c, "First");
		final Thread second = new Thread(c, "Second");

		// Threads start executing
		first.start();
		second.start();

		// Main thread will wait for both
		// threads to complete execution
		first.join();
		second.join();

		// Printing final value of count variable
		System.out.println(c.getCount());
	}
}
