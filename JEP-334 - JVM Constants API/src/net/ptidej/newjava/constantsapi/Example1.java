package net.ptidej.newjava.constantsapi;

import java.lang.constant.ClassDesc;
import java.lang.constant.DirectMethodHandleDesc;
import java.lang.constant.MethodHandleDesc;
import java.lang.constant.MethodTypeDesc;

public class Example1 {
	public static void main(final String[] args) {
		final ClassDesc example1ClassDesc = ClassDesc.of("net.ptidej.newjava.constantsapi", "Example1");
		final MethodTypeDesc mainMethodTypeDesc = MethodTypeDesc.of(example1ClassDesc);
		final MethodHandleDesc mainMethodHandleDesc = MethodHandleDesc.ofMethod(DirectMethodHandleDesc.Kind.STATIC,
				example1ClassDesc, "main", mainMethodTypeDesc);

		System.out.println("Class descriptor:         " + example1ClassDesc);
		System.out.println("Method type descriptor:   " + mainMethodTypeDesc);
		System.out.println("Method handle descriptor: " + mainMethodHandleDesc);
	}
}
