package net.ptidej.newjava.structuredconcurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Version2 extends AbstractOrder {
	private final ExecutorService executor;

	public Version2() {
		this.executor = Executors.newFixedThreadPool(10);
	}

	Response handle() throws ExecutionException, InterruptedException {
		final Future<String> user = this.executor.submit(() -> findUser());
		final Future<Integer> order = this.executor.submit(() -> fetchOrder());
		final String theUser = user.get(); // Join findUser, 
		final int theOrder = order.get(); // Join fetchOrder
		return new Response(theUser, theOrder);
	}
}
