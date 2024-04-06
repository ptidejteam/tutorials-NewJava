package net.ptidej.newjava.constantdynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class Example1 {
	// Simple test of ByteBuddy
	@Test
	public void test1() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		final Class<?> dynamicType = new ByteBuddy().subclass(Object.class).method(ElementMatchers.named("toString"))
				.intercept(FixedValue.value("Hello World!")).make().load(Example1.class.getClassLoader()).getLoaded();

		final Object instance = dynamicType.getDeclaredConstructor().newInstance();
		assertThat(instance.toString()).isEqualTo("Hello World!");
	}
}
