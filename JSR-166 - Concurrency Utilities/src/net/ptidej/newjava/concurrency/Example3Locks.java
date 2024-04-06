// https://www.digitalocean.com/community/tutorials/java-lock-example-reentrantlock
package net.ptidej.newjava.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example3Locks {

}

class SynchronizedLockExample implements Runnable {
	private Resource resource;

	public SynchronizedLockExample(final Resource aResource) {
		this.resource = aResource;
	}

	@Override
	public void run() {
		synchronized (this.resource) {
			this.resource.doSomething();
		}
		this.resource.doLogging();
	}
}

class ConcurrencyLockExample implements Runnable {
	private Resource resource;
	private Lock lock;

	public ConcurrencyLockExample(final Resource aResource) {
		this.resource = aResource;
		this.lock = new ReentrantLock();
	}

	@Override
	public void run() {
		try {
			if (this.lock.tryLock(10, TimeUnit.SECONDS)) {
				resource.doSomething();
			}
		} catch (final InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			this.lock.unlock();
		}
		this.resource.doLogging();
	}
}

class Resource {
	public void doSomething() {
		// Do some operation, DB read, write etc.
	}

	public void doLogging() {
		// Logging, no need for thread safety
	}
}