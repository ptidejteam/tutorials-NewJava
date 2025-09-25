package net.ptidej.newjava.structuredconcurrency;

import java.io.IOException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class Version3 extends AbstractOrder {
	Response handle() {
		try (final var scope = StructuredTaskScope.open()) {
			final Subtask<String> user = scope.fork(() -> findUser());
			final Subtask<Integer> order = scope.fork(() -> fetchOrder());

			scope.join(); // Join subtasks, propagating exceptions

			final String theUser = user.get();
			final int theOrder = order.get();
			return new Response(theUser, theOrder);
		}
		catch (final StructuredTaskScope.FailedException
				| InterruptedException e) {

			final Throwable cause = e.getCause();
			switch (cause) {
			case IOException ioe -> System.out.println(ioe);
			default -> System.out.println(cause);
			}
		}
		return null;
	}
}
