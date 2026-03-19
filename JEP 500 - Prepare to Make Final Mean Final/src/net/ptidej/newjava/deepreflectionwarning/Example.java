package net.ptidej.newjava.deepreflectionwarning;

import java.lang.reflect.Field;

public class Example {
	private final int i;

	public Example() {
		this.i = 100;
	}

	public static void main(final String[] args) throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {

		final Example example = new Example();
		System.out.println(example.i);

		final Class<?> classE = Example.class;
		final Field fieldi = classE.getDeclaredField("i");
		fieldi.setAccessible(true);
		fieldi.setInt(example, 42);
		System.out.println(example.i);

		final C c = new C();
		System.out.println(c.getX());

		final Class<?> classC = C.class;
		final Field fieldx = classC.getDeclaredField("x");
		fieldx.setAccessible(true);
		fieldx.setInt(c, 42);
		System.out.println(c.getX());

	}

	public int getI() {
		return this.i;
	}
}
