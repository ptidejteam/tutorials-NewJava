// Enum values (also called constants sometimes) are actually anonymous class of the type of the enclosing enum declaration.
// https://youtrack.jetbrains.com/issue/IDEA-236735/Java-enum-constant-is-called-Anonymous-inner-class

package net.ptidej.newjava.enums;

import java.lang.reflect.Method;

interface Interface3 {
	public boolean foo();
}

abstract class SimulatedEnum3 implements Interface3 {
	private static int numberOfIDs = 0;

	public static final SimulatedEnum3 YES = new SimulatedEnum3() {
		@Override
		public boolean foo() {
			return true;
		}
	};

	public static final SimulatedEnum3 NO = new SimulatedEnum3() {
		@Override
		public boolean foo() {
			return false;
		}
	};

	public final int constantID;

	private SimulatedEnum3() {
		// The final constructor in MyEnum1 prevents
		// subclasses even without the keyword final.

		this.constantID = SimulatedEnum3.numberOfIDs;
		SimulatedEnum3.numberOfIDs++;
	}

	public boolean bar() {
		return this.foo();
	}
}

enum RealEnum3 implements Interface3 {
	YES {
		public boolean foo() {
			return true;
		};
	},
	NO {
		public boolean foo() {
			return false;
		};
	};

	public boolean bar() {
		return this.foo();
	}
}

public class Example3 {
	public static void main(final String[] args) {
		Example3.simulatedEnums();
		System.out.println("\n***\n");
		Example3.realEnums();
	}

	private static void simulatedEnums() {
		System.out.println(SimulatedEnum3.YES.bar());
		System.out.println(SimulatedEnum3.NO.bar());

		System.out.print("Superclass: ");
		System.out.println(SimulatedEnum3.NO.getClass().getSuperclass());
		System.out.print("Class:      ");
		System.out.println(SimulatedEnum3.NO.getClass());
		for (final Method method : SimulatedEnum3.NO.getClass().getDeclaredMethods()) {
			System.out.print("\tMethods: ");
			System.out.println(method);
		}

		final SimulatedEnum3 v1 = SimulatedEnum3.YES; // Example
		switch (v1) {
		case SimulatedEnum3.YES:
			System.out.println("YES");
			break;
		case SimulatedEnum3.NO:
			System.out.println("NO");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + v1);
		}

		final int v2 = 42; // Example
		switch (v2) {
		case SimulatedEnum3.YES.constantID: // case expressions must be constant expressions
			System.out.println("YES");
			break;
		case SimulatedEnum3.NO.constantID: // case expressions must be constant expressions
			System.out.println("NO");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + v2);
		}
	}

	private static void realEnums() {
		System.out.println(RealEnum3.YES.bar());
		System.out.println(RealEnum3.NO.bar());

		System.out.print("Superclass: ");
		System.out.println(RealEnum3.NO.getClass().getSuperclass());
		System.out.print("Class:      ");
		System.out.println(RealEnum3.NO.getClass());
		for (final Method method : RealEnum3.NO.getClass().getDeclaredMethods()) {
			System.out.print("\tMethods: ");
			System.out.println(method);
		}

		final RealEnum3 v = RealEnum3.values()[1]; // Example
		switch (v) {
		case YES:
			System.out.println("YES");
			break;
		case NO:
			System.out.println("NO");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + v);
		}
	}
}