// https://www.baeldung.com/java-unsigned-arithmetic
package net.ptidej.newjava.unsignedarithmetic;

public class Example1 {
	public static void main(String[] args) {
		int positive = Integer.MAX_VALUE;
		int negative = Integer.MIN_VALUE;

		int signedComparison = Integer.compare(positive, negative);
		if (signedComparison > 0) {
			System.out.println("Positive > negative (signed comparison)");
		}

		int unsignedComparison = Integer.compareUnsigned(positive, negative);
		if (unsignedComparison < 0) {
			System.out.println("Positive NOT > negative (unsigned comparison)");
		}
	}
}
