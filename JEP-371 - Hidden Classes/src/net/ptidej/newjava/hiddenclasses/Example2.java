// https://www.baeldung.com/java-hidden-classes
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

public class Example2 {
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
		final Class<?> hiddenClassClass = lookup.defineHiddenClass(bytes, true, ClassOption.STRONG).lookupClass();
		InfoPrinter.printInfo(hiddenClassClass);
	}
}
