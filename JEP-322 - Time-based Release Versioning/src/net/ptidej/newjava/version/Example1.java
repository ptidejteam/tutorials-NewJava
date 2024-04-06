package net.ptidej.newjava.version;

import java.lang.Runtime.Version;

public class Example1 {
	public static void main(final String[] args) {
		final Version version = Runtime.version();

		System.out.println(version);
		System.out.print("Feature: ");
		System.out.println(version.feature());
		System.out.print("Interim: ");
		System.out.println(version.interim());
		System.out.print("Update: ");
		System.out.println(version.update());
		System.out.print("Patch: ");
		System.out.println(version.patch());

		System.out.print("Build: ");
		System.out.println(version.build());
		System.out.print("Optional: ");
		System.out.println(version.optional());
		System.out.print("Pre: ");
		System.out.println(version.pre());

		System.out.print("Major: ");
		System.out.println(version.major());
		System.out.print("Minor: ");
		System.out.println(version.minor());
	}
}
