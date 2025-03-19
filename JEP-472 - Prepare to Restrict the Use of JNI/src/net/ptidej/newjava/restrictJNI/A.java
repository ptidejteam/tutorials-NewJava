package net.ptidej.newjava.restrictJNI;

public class A {

	static {
		try {
			System.out.println(
					"A.class.getClassLoader() = " + A.class.getClassLoader());
			System.loadLibrary("net_ptidej_newjava_restrictJNI_A");
		}
		catch (final UnsatisfiedLinkError error) {
			System.err.println(error);
		}
	}

	public static native int bar();

}
