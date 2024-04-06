package net.ptidej.modularisation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ServiceLoader;

import net.ptidej.modularisation.kernel.Interface;

public class Client {
	public static void main(final String[] args)
			throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException,
			IllegalArgumentException, NoSuchMethodException, SecurityException {

		final ServiceLoader<Interface> providers = ServiceLoader.load(Interface.class);
		final Interface aki = providers.findFirst().orElse(null);
		aki.foo();

		System.out.println("Call on public implementation: ");
		final Class<? extends Interface> implementation1 = Class
				.forName("net.ptidej.modularisation.kernel.impl.Implementation").asSubclass(Interface.class);
		final Interface aki1 = implementation1.getDeclaredConstructor().newInstance();
		final Method[] methods1 = implementation1.getDeclaredMethods();
		for (final Method method : methods1) {
			System.out.print('\t');
			System.out.print(method.getName());
			System.out.print(": ");
			try {
				method.setAccessible(true);
				method.invoke(aki1, new Object[0]);
			} catch (final RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("Call on hidden implementation: ");
		final Class<? extends Interface> implementation2 = Class
				.forName("net.ptidej.modularisation.kernel.impl.HiddenImplementation").asSubclass(Interface.class);
		final Interface aki2 = implementation2.getDeclaredConstructor().newInstance();
		final Method[] methods2 = implementation2.getDeclaredMethods();
		for (final Method method : methods2) {
			System.out.print('\t');
			System.out.print(method.getName());
			System.out.print(": ");
			try {
				method.setAccessible(true);
				method.invoke(aki2, new Object[0]);
			} catch (final RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
