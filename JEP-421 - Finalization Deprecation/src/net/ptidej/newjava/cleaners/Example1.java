// https://howtodoinjava.com/java/basics/java-cleaners/
package net.ptidej.newjava.cleaners;

import java.lang.ref.Cleaner;

class Resource {
	// Some (expensive) resource
}

class ResourceAccessingClass implements AutoCloseable {
	private final Cleaner cleaner = Example1.getCleaner();
	private final Cleaner.Cleanable cleanable;

	// This is the resource to be cleaned after usage
	private final Resource resource;

	public ResourceAccessingClass() {
		this.resource = new Resource();
		this.cleanable = cleaner.register(this, () -> {
			// Perform cleanup actions
			// resource.release();
			System.out.println("Resource cleaned up");
		});
	}

	public void businessOperation1() {
		// Access the resource
		System.out.print("Inside businessOperation1() with ");
		System.out.println(this.resource);
	}

	public void businessOperation2() {
		// Access the resource
		System.out.print("Inside businessOperation2() with ");
		System.out.println(this.resource);
	}

	@Override
	public void close() throws Exception {
		cleanable.clean();
	}
}

public class Example1 {
	private static final Cleaner CLEANER = Cleaner.create();

	public static Cleaner getCleaner() {
		return Example1.CLEANER;
	}

	public static void main(final String[] args) throws Exception {
		// Implicit Cleanup
		try (final ResourceAccessingClass o = new ResourceAccessingClass()) {
			// Safely use the resource
			o.businessOperation1();
			o.businessOperation2();
		}

		// Explicit Cleanup
		final ResourceAccessingClass o = new ResourceAccessingClass();
		o.businessOperation1();
		o.businessOperation2();
		o.close();
	}
}
