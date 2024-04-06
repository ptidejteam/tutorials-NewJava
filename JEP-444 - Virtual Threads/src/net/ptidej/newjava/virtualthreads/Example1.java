// https://davidvlijmincx.com/posts/create_virtual_threads_with_project_loom/
package net.ptidej.newjava.virtualthreads;

import java.util.concurrent.ThreadFactory;

public class Example1 {
	public static void main(final String[] args) throws InterruptedException {
		final Runnable printThread = () -> System.out.println(Thread.currentThread());

		new Thread(printThread).start();

		final ThreadFactory threadFactory1 = Thread.ofPlatform().factory();
		final Thread platformThread2 = threadFactory1.newThread(printThread);
		platformThread2.start();

		Thread.startVirtualThread(printThread);

		final ThreadFactory threadFactory2 = Thread.ofVirtual().factory();
		final Thread virtualThread2 = threadFactory2.newThread(printThread);
		virtualThread2.start();

		Thread.sleep(2_000);
	}
}
