package net.ptidej.newjava.hiddenclasses;

public interface InfoPrinter {
	public static void printInfo(final Class<?> aClass) {
		System.out.println("***");
		System.out.println(aClass);
		System.out.print("\tCanonical name: ");
		System.out.println(aClass.getCanonicalName());
		System.out.print("\t          Name: ");
		System.out.println(aClass.getName());
		System.out.print("\t      isHidden? ");
		System.out.println(aClass.isHidden());
		System.out.println();
	}
}
