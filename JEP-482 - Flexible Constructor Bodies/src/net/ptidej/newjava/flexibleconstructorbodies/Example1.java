package net.ptidej.newjava.flexibleconstructorbodies;

import java.math.BigDecimal;
import java.math.BigInteger;

class ValidationWithAuxiliaryMethod extends BigDecimal {
	private static long checkPositive(final long value) {
		if (value <= 0)
			throw new IllegalArgumentException("The value should be positive");
		return value;
	}

	public ValidationWithAuxiliaryMethod(final long value) {
		super(checkPositive(value));
	}
}

class ValidationInConstructor extends BigInteger {
	public ValidationInConstructor(final long value) {
		if (value <= 0)
			throw new IllegalArgumentException("The value should be positive");
		super(value);
	}

}

public class Example1 {
	public static void main(final String[] args) throws InterruptedException {
		final ValidationWithAuxiliaryMethod v1 = new ValidationWithAuxiliaryMethod(
				0);
		try {
			final ValidationWithAuxiliaryMethod v2 = new ValidationWithAuxiliaryMethod(
					-1);
		}
		catch (final IllegalArgumentException e) {
			// Should throw an exception
		}
		final ValidationInConstructor v3 = new ValidationInConstructor(0);
		try {
			final ValidationInConstructor v4 = new ValidationInConstructor(-1);
		}
		catch (final IllegalArgumentException e) {
			// Should throw an exception
		}
	}
}
