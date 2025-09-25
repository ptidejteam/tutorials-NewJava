package net.ptidej.newjava.structuredconcurrency;

import java.io.IOException;

public class Version1 extends AbstractOrder {
	Response handle() throws IOException {
		final String theUser = this.findUser();
		final int theOrder = this.fetchOrder();
		return new Response(theUser, theOrder);
	}
}
