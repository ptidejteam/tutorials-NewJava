// https://gee.cs.oswego.edu/dl/concurrency-interest/jsr166-slides.pdf
package net.ptidej.newjava.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example1Executors {
	private static class RunnableExample implements Runnable {
		private final String name;

		public RunnableExample(final String aName) {
			this.name = aName;
		}

		@Override
		public void run() {
			int count = 0;
			for (int i = 0; i < 10000000; i++) {
				count++;
			}
			System.out.print(this.name);
			System.out.print(": ");
			System.out.println(count);
		}
	}

	public static void main(String[] args) {
		final Runnable runnable1 = new RunnableExample("executor.execute(...)");
		final ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.execute(runnable1);

		final Runnable runnable2 = new RunnableExample("new Thread(...)");
		new Thread(runnable2).start();
	}
}
