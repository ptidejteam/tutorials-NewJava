package net.ptidej.newjava.constantdynamic;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<SomeExpensiveData> {
	private static MyCallable UniqueInstance;

	public static MyCallable getInstance() {
		if (MyCallable.UniqueInstance == null) {
			synchronized (MyCallable.class) {
				if (MyCallable.UniqueInstance == null) {
					MyCallable.UniqueInstance = new MyCallable();
				}
			}
		}
		return MyCallable.UniqueInstance;
	}

	private volatile SomeExpensiveData someExpensiveData;

	private MyCallable() {
		this.someExpensiveData = new SomeExpensiveData();
	}

	@Override
	public SomeExpensiveData call() throws Exception {
		return this.someExpensiveData;
	}
}
