package net.ptidej.newjava.scopedvalues;

class Request {
}

class Response {
}

class FrameworkContext {
}

class Framework {
	private final static ScopedValue<FrameworkContext> CONTEXT = ScopedValue
			.newInstance();

	void serve(Request request, Response response) {
		var context = createContext(request);
		ScopedValue.runWhere(CONTEXT, context,
				() -> Application.handle(request, response));
	}

	public PersistedObject readKey(String key) {
		var context = CONTEXT.get(); // (3)
		var db = getDBConnection(context);
		db.readKey(key);
	}

}

public class Example1 {
	public static void main(final String[] args) throws InterruptedException {
	}
}
