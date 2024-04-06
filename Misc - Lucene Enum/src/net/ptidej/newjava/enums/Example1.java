package net.ptidej.newjava.enums;

import java.lang.reflect.Method;

interface Interface {
	public boolean foo();
}

abstract class SimulatedEnum implements Interface {
	private static int numberOfIDs = 0;

	public static final SimulatedEnum YES = new SimulatedEnum() {
		@Override
		public boolean foo() {
			return true;
		}
	};

	public static final SimulatedEnum NO = new SimulatedEnum() {
		@Override
		public boolean foo() {
			return false;
		}
	};

	public final int constantID;

	private SimulatedEnum() {
		// The final constructor in MyEnum1 prevents
		// subclasses even without the keyword final.

		this.constantID = SimulatedEnum.numberOfIDs;
		SimulatedEnum.numberOfIDs++;
	}

	public boolean bar() {
		return this.foo();
	}
}

// The constructor SubEnum() cannot be compiled
// with error:
//	Implicit super constructor SimulatedEnum() is not visible. Must explicitly invoke another constructor
/*
class SubEnum extends SimulatedEnum {
	public SubEnum() {
	}

	@Override
	public boolean foo() {
		return false;
	}
}
*/

// Enum values (also called constants sometimes) are actually anonymous class of the type of the enclosing enum declaration.
// https://youtrack.jetbrains.com/issue/IDEA-236735/Java-enum-constant-is-called-Anonymous-inner-class

enum RealEnum implements Interface {
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

public class Example1 {
	public static void main(final String[] args) {
		Example1.simulatedEnums();
		System.out.println("\n***\n");
		Example1.realEnums();
	}

	private static void simulatedEnums() {
		System.out.println(SimulatedEnum.YES.bar());
		System.out.println(SimulatedEnum.NO.bar());

		System.out.print("Superclass: ");
		System.out.println(SimulatedEnum.NO.getClass().getSuperclass());
		System.out.print("Class:      ");
		System.out.println(SimulatedEnum.NO.getClass());
		for (final Method method : SimulatedEnum.NO.getClass().getDeclaredMethods()) {
			System.out.print("\tMethods: ");
			System.out.println(method);
		}

		final int v = 42; // Example
		switch (v) {
		case SimulatedEnum.YES.constantID: // case expressions must be constant expressions

			System.out.println("YES");
			break;
		case SimulatedEnum.NO.constantID: // case expressions must be constant expressions
			System.out.println("NO");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + v);
		}
	}

	private static void realEnums() {
		System.out.println(RealEnum.YES.bar());
		System.out.println(RealEnum.NO.bar());

		System.out.print("Superclass: ");
		System.out.println(RealEnum.NO.getClass().getSuperclass());
		System.out.print("Class:      ");
		System.out.println(RealEnum.NO.getClass());
		for (final Method method : RealEnum.NO.getClass().getDeclaredMethods()) {
			System.out.print("\tMethods: ");
			System.out.println(method);
		}

		final RealEnum v = RealEnum.values()[1]; // Example
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