package net.ptidej.newjava.ffm;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Arrays;

public class Example1 {
	public static void main(final String[] args) {
		final Example1 sorter = new Example1();
		final String[] friends = { "Rick Deckard", "Roy Batty", "Harry Bryant", "Hannibal Chew", "Gaff", "Holden",
				"Leon Kowalski", "Taffey Lewis", "Pris", "Rachael", "J.F. Sebastian", "Dr. Eldon Tyrell", "Zhora",
				"Hodge", "Mary" };

		System.out.println("Input : " + Arrays.toString(friends));
		System.out.println("Output: " + Arrays.toString(sorter.sort(friends)));
	}

	private String[] sort(final String[] strings) {
		// Find foreign function on the C library path
		final Linker linker = Linker.nativeLinker();
		final SymbolLookup stdlib = linker.defaultLookup();
		final MemorySegment radixSort = stdlib.find("radixsort").orElseThrow();
		final MethodHandle methodHandle = linker.downcallHandle(radixSort, FunctionDescriptor
				.ofVoid(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.ADDRESS, ValueLayout.JAVA_CHAR));

		// Use try-with-resources to manage the lifetime of off-heap memory
		try (final Arena arena = Arena.ofConfined()) {
			// Allocate a region of off-heap memory to store pointers
			final MemorySegment pointers = arena.allocateArray(ValueLayout.ADDRESS, strings.length);

			// Copy the strings from on-heap to off-heap
			for (int i = 0; i < strings.length; i++) {
				final MemorySegment cString = arena.allocateUtf8String(strings[i]);
				pointers.setAtIndex(ValueLayout.ADDRESS, i, cString);
			}

			// Sort the off-heap data by calling the foreign function
			methodHandle.invoke(pointers, strings.length, MemorySegment.NULL, '\0');

			// Copy the (reordered) strings from off-heap to on-heap
			for (int i = 0; i < strings.length; i++) {
				MemorySegment cString = pointers.getAtIndex(ValueLayout.ADDRESS, i);
				cString = cString.reinterpret(Long.MAX_VALUE);
				strings[i] = cString.getUtf8String(0);
			}
		} catch (final Throwable e) {
			throw new RuntimeException(e);
		}

		return strings;
	}
}