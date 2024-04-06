// Enum values (also called constants sometimes) are actually anonymous class of the type of the enclosing enum declaration.
// https://youtrack.jetbrains.com/issue/IDEA-236735/Java-enum-constant-is-called-Anonymous-inner-class

package net.ptidej.newjava.enums;

import java.lang.reflect.Method;

interface PseudoEnum0 {
	int YES = 0;
	int NO = 1;
}

interface Interface1 {
	public boolean foo();
}

enum RealEnum1 implements Interface1 {
	YES {
		public boolean foo() {
			return true;
		}
	},
	NO {
		public boolean foo() {
			return false;
		}
	};

	public boolean bar() {
		return this.foo();
	}
}

abstract class SimulatedEnum1 implements Interface1 {
	public static final SimulatedEnum1 YES = new SimulatedEnum1() {
		@Override
		public boolean foo() {
			return true;
		}
	};

	public static final SimulatedEnum1 NO = new SimulatedEnum1() {
		@Override
		public boolean foo() {
			return false;
		}
	};

	private SimulatedEnum1() {
	}

	public boolean bar() {
		return this.foo();
	}
}

public class Example1 {
	public static void main(final String[] args) {
		Example1.realEnums();
		System.out.println("\n***\n");
		Example1.simulatedEnums();
	}

	private static void realEnums() {
		System.out.println(RealEnum1.YES.bar());
		System.out.println(RealEnum1.NO.bar());

		System.out.print("Superclass: ");
		System.out.println(RealEnum1.NO.getClass().getSuperclass());
		System.out.print("Class:      ");
		System.out.println(RealEnum1.NO.getClass());
		for (final Method method : RealEnum1.NO.getClass().getDeclaredMethods()) {
			System.out.print("\tMethods: ");
			System.out.println(method);
		}
	}

	private static void simulatedEnums() {
		System.out.println(SimulatedEnum1.YES.bar());
		System.out.println(SimulatedEnum1.NO.bar());

		System.out.print("Superclass: ");
		System.out.println(SimulatedEnum1.NO.getClass().getSuperclass());
		System.out.print("Class:      ");
		System.out.println(SimulatedEnum1.NO.getClass());
		for (final Method method : SimulatedEnum1.NO.getClass().getDeclaredMethods()) {
			System.out.print("\tMethods: ");
			System.out.println(method);
		}
	}
}