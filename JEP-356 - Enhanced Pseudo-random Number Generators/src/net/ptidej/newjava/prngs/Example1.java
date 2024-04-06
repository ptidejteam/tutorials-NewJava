// https://www.baeldung.com/java-17-random-number-generators
package net.ptidej.newjava.prngs;

import java.util.Comparator;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class Example1 {
	public static void main(final String[] args) {
		RandomGeneratorFactory.all().sorted(Comparator.comparing(RandomGeneratorFactory::name))
				.forEach(factory -> System.out.println(String.format("%s\t%s\t%s\t%s", factory.group(), factory.name(),
						factory.isJumpable(), factory.isSplittable())));

		final RandomGenerator generator = RandomGenerator.getDefault();
		System.out.println(generator.nextInt());
	}
}
