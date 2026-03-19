/*
 *  https://openjdk.org/jeps/526
 */
package net.ptidej.newjava.lazyconstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Example {
	private static final int POOL_SIZE = 0;

	// OLD:
	// private Logger logger = null;

	// NEW:
	private final LazyConstant<Logger> logger = LazyConstant
			.of(() -> Logger.create(Example.class));

	void submitOrder(final User user, final List<Product> products) {
		logger.get().info("order started");
		// ...
		logger.get().info("order submitted");
	}

	// OLD:
	// static final UserService USERS = new UserService();

	// NEW:
	static final LazyConstant<UserService> USERS = LazyConstant
			.of(UserService::new);

	public static UserService users() {
		return USERS.get();
	}

	// OLD:
	// static final List ORDERS = new ArrayList<OrderController>();

	// NEW:
	static final List<OrderController> ORDERS1 = List.ofLazy(Example.POOL_SIZE,
			_ -> new OrderController());

	public static OrderController orders1() {
		long index = Thread.currentThread().threadId() % Example.POOL_SIZE;
		return ORDERS1.get((int) index);
	}

	// NEW:
	static final Map<String, OrderController> ORDERS2 = Map.ofLazy(
			Set.of("Customers", "Internal", "Testing"),
			_ -> new OrderController());

	public static OrderController orders2() {
		String threadName = Thread.currentThread().getName();
		return ORDERS2.get(threadName);
	}

}