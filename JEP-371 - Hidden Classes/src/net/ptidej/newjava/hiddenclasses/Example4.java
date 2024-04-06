// https://www.javacodegeeks.com/2024/02/explore-java-15s-hidden-classes-with-code-examples.html
package net.ptidej.newjava.hiddenclasses;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;
import java.lang.invoke.MethodType;

public class Example4 {
	public static void main(final String[] args) throws Throwable {
		// Dynamically create a plugin class
		final MethodHandles.Lookup lookup = MethodHandles.lookup();
		final MethodType mt = MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null);
		// CODE SHOWN IN THE URL IS NOT VALID ANYMORE!
		final Class<?> pluginClass = lookup.defineHiddenClass(new byte[] {}, true, ClassOption.STRONG).lookupClass();

		// Instantiate the plugin class and invoke its method
		final Object pluginInstance = pluginClass.getDeclaredConstructor().newInstance();
		final MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(pluginClass, lookup);
		privateLookup.findVirtual(pluginClass, "execute", mt).invokeExact(pluginInstance, "Hello from the plugin!");
	}
}
