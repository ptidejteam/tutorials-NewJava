package net.ptidej.newjava.restrictJNI;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(final String[] args)
			throws ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {

		// Current module
		final Module currentModule = A.class.getModule();
		System.out.println(currentModule);
		System.out.println(currentModule.getLayer().hashCode());
		System.out.println(currentModule.isNativeAccessEnabled());

		System.out.println("Calling A.bar()");
		System.out.println(A.bar());

		// New module
		final ModuleFinder moduleFinder = ModuleFinder.of(Path.of("bin/"));
		final ModuleLayer bootLayer = ModuleLayer.boot();
		final Configuration cf = Configuration.resolve(moduleFinder,
				List.of(bootLayer.configuration()), ModuleFinder.of(),
				Set.of("myModule"));
		final ModuleLayer.Controller controller = ModuleLayer
				.defineModulesWithOneLoader(cf, List.of(bootLayer),
						A.class.getClassLoader());
		final ModuleLayer newLayer = controller.layer();
		final Module newModule = List.copyOf(newLayer.modules()).getFirst();
		System.out.println(newModule);
		System.out.println(newModule.getLayer().hashCode());
		System.out.println(newModule.isNativeAccessEnabled());

		System.out.println("Calling A.bar()");
		controller.enableNativeAccess(newModule);
		// final Class<?> classMain = Class.forName(newModule,
		//		"net.ptidej.newjava.restrictJNI.A");
		final Class<?> classMain = newModule.getClassLoader()
				.loadClass("net.ptidej.newjava.restrictJNI.A");
		// final Method fooMethod = classMain.getDeclaredMethod("bar");
		// fooMethod.invoke(null, new Object[0]);
	}

}