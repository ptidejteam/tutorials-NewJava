package net.ptidej.newjava.constantdynamic;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType.Unloaded;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;

public class Example2 {
	// Create a volatile instance variable
	@Test
	public void test_Volatile1() throws Throwable {
		System.out.println("\ntest_Volatile1()");

		final Callable<SomeExpensiveData> first = MyCallable.getInstance();
		final Callable<SomeExpensiveData> second = MyCallable.getInstance();
		System.out.println("\tCallable instances created");
		assertThat(first.call()).isEqualTo(second.call());
	}

	// Create a volatile instance variable
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void test_Volatile2() throws Throwable {
		System.out.println("\ntest_Volatile2()");

		// Really: Unloaded<Callable<SomeExpensiveData>>
		final Unloaded<Callable> unloaded = new ByteBuddy().subclass(Callable.class)
				.method(ElementMatchers.named("call")).intercept(FixedValue.value(new SomeExpensiveData())).make();

		final File classFile = new File("output");
		unloaded.saveIn(classFile);

		final Constructor<? extends Callable> loaded = unloaded.load(Example2.class.getClassLoader()).getLoaded()
				.getConstructor();
		final Callable<SomeExpensiveData> first = loaded.newInstance();
		final Callable<SomeExpensiveData> second = loaded.newInstance();
		System.out.println("\tCallable instances created");
		assertThat(first.call()).isEqualTo(second.call());
	}

	// Create a CONSTANT_dynamic
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void test_CONSTANT_Dynamic() throws Throwable {
		System.out.println("\ntest_CONSTANT_Dynamic()");

		// Really: Unloaded<Callable<SomeExpensiveData>>
		final Unloaded<Callable> unloaded = new ByteBuddy().subclass(Callable.class)
				.method(ElementMatchers.named("call"))
				.intercept(
						FixedValue.value(JavaConstant.Dynamic.ofInvocation(SomeExpensiveData.class.getConstructor())))
				.make();

		final File classFile = new File("output");
		unloaded.saveIn(classFile);

		final Constructor<? extends Callable> loaded = unloaded.load(Example2.class.getClassLoader()).getLoaded()
				.getConstructor();
		final Callable<SomeExpensiveData> first = loaded.newInstance();
		final Callable<SomeExpensiveData> second = loaded.newInstance();
		System.out.println("\tCallable instances created");
		assertThat(first.call()).isEqualTo(second.call());
	}
}
