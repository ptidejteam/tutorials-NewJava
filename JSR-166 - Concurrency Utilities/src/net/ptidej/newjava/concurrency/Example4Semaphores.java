// https://mkyong.com/java/java-semaphore-examples/
package net.ptidej.newjava.concurrency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Example4Semaphores {
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	public Example4Semaphores() {
		this.semaphore = new Semaphore(1);
	}

	private final Semaphore semaphore;

	private void connect(final String user) throws InterruptedException {
		System.out.println(getCurrentDateTime() + " : " + user + " waiting for semaphore");
		this.semaphore.acquire();
		System.out.println(getCurrentDateTime() + " : " + user + " acquired semaphore");

		Thread.sleep(1000); // Some work...

		this.semaphore.release();
		System.out.println(getCurrentDateTime() + " : " + user + " released semaphore");

	}

	public static void main(final String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(5);
		final Example4Semaphores task = new Example4Semaphores();

		// Submit 3 tasks
		executor.submit(() -> {
			try {
				task.connect("mkyong");
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});

		executor.submit(() -> {
			try {
				task.connect("yflow");
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});

		executor.submit(() -> {
			try {
				task.connect("zilap");
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		});

		executor.shutdown();

	}

	private static String getCurrentDateTime() {
		return sdf.format(new Date());
	}
}