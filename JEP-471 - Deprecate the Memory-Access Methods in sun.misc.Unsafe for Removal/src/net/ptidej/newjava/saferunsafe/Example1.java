// https://www.baeldung.com/java-unsafe
package net.ptidej.newjava.saferunsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

class OffHeapArray {
	private final static int BYTE_SIZE = 1;
	private long size;
	private long address;

	public OffHeapArray(final long size)
			throws NoSuchFieldException, IllegalAccessException {
		this.size = size;
		this.address = this.getUnsafe().allocateMemory(this.size * BYTE_SIZE);
	}

	private Unsafe getUnsafe()
			throws IllegalAccessException, NoSuchFieldException {

		final Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		return (Unsafe) f.get(null);
	}

	public void set(final long idx, final byte value)
			throws NoSuchFieldException, IllegalAccessException {
		this.getUnsafe().putByte(this.address + idx * BYTE_SIZE, value);
	}

	public int get(final long idx)
			throws NoSuchFieldException, IllegalAccessException {
		return this.getUnsafe().getByte(this.address + idx * BYTE_SIZE);
	}

	public long size() {
		return this.size;
	}

	public void freeMemory()
			throws NoSuchFieldException, IllegalAccessException {
		this.getUnsafe().freeMemory(this.address);
	}
}

public class Example1 {
	public static void main(final String[] args)
			throws NoSuchFieldException, IllegalAccessException {
		final OffHeapArray oha = new OffHeapArray(1);
		oha.set(0, (byte) 42);
		oha.set(1, (byte) 84);
		oha.set(2, (byte) 111);

		System.out.println(oha.get(0));
		System.out.println(oha.get(1));
		System.out.println(oha.get(2));

		oha.freeMemory();
	}
}