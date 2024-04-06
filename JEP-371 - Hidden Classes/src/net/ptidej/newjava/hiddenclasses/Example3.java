// https://www.baeldung.com/java-hidden-classes
// https://foojay.io/today/what-are-hidden-classes-in-java-15/
package net.ptidej.newjava.hiddenclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;

import org.apache.commons.io.IOUtils;

/*
class HiddenClass {
	public String convertToUpperCase(final String s) {
		return s.toUpperCase();
	}
}
*/

class MyClassLoader extends ClassLoader {
	public MyClassLoader(final ClassLoader aParentClassLoader) {
		super(aParentClassLoader);
	}

	public Class<?> loadClass(String className, byte[] classBytes) throws ClassNotFoundException, NoClassDefFoundError {
		final Class<?> result = this.defineClass(className, classBytes, 0, classBytes.length);
		return result;
	}
}

public class Example3 {
	@SuppressWarnings("unused")
	private static void generateHiddenClassBytes() throws ClassNotFoundException, IOException {
		final Class<?> hiddenClassClass = Class.forName("net.ptidej.newjava.hiddenclasses.HiddenClass");
		final String className = hiddenClassClass.getName();
		final String classPath = className.replace('.', '/') + ".class";
		final InputStream stream = hiddenClassClass.getClassLoader().getResourceAsStream(classPath);
		final byte[] bytes = IOUtils.toByteArray(stream);
		System.out.println(bytes);
	}

	public static void main(final String[] args) throws ClassNotFoundException, IOException, IllegalAccessException {
		final InputStream stream = new FileInputStream(new File("rsc/HiddenClass.class"));
		final byte[] bytes = IOUtils.toByteArray(stream);
		final MethodHandles.Lookup lookup = MethodHandles.lookup();
		final Class<?> hiddenClassClass1 = lookup.defineHiddenClass(bytes, true, ClassOption.STRONG).lookupClass();
		InfoPrinter.printInfo(hiddenClassClass1);

		final Class<?> notHiddenClassClass1 = lookup.defineClass(bytes);
		InfoPrinter.printInfo(notHiddenClassClass1);

		final MyClassLoader notHiddenClassClassLoader = new MyClassLoader(lookup.getClass().getClassLoader());
		final Class<?> notHiddenClassClass2 = notHiddenClassClassLoader
				.loadClass("net.ptidej.newjava.hiddenclasses.HiddenClass", bytes);
		InfoPrinter.printInfo(notHiddenClassClass2);

		try {
			final Class<?> hiddenClassClass2 = Class.forName(hiddenClassClass1.getName());
			InfoPrinter.printInfo(hiddenClassClass2);
		} catch (final ClassNotFoundException e) {
			System.err.println("***");
			System.err.println("As expected, the hidden class cannot be discovered!");
			System.err.println();
		}
		try {
			final Class<?> hiddenClassClass3 = lookup.findClass(hiddenClassClass1.getName());
			InfoPrinter.printInfo(hiddenClassClass3);
		} catch (final ClassNotFoundException | IllegalAccessException e) {
			System.err.println("***");
			System.err.println("As expected, the hidden class cannot be discovered!");
			System.err.println();
		}
	}
}
