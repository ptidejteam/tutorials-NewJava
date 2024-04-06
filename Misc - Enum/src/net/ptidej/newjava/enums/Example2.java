// Enum values (also called constants sometimes) are actually anonymous class of the type of the enclosing enum declaration.
// https://youtrack.jetbrains.com/issue/IDEA-236735/Java-enum-constant-is-called-Anonymous-inner-class

package net.ptidej.newjava.enums;

import java.lang.reflect.Method;

interface Interface2 {
	public boolean foo();
}

abstract class SimulatedEnum2 implements Interface2 {
	public static final SimulatedEnum2 YES = new SimulatedEnum2() {
		@Override
		public boolean foo() {
			return true;
		}
	};

	public static final SimulatedEnum2 NO = new SimulatedEnum2() {
		@Override
		public boolean foo() {
			return false;
		}
	};

	private SimulatedEnum2() {
		// The final constructor in MyEnum1 prevents
		// subclasses even without the keyword final.
	}

	public boolean bar() {
		return this.foo();
	}
}

// The constructor SubEnum() cannot be compiled
// with error:
//	Implicit super constructor SimulatedEnum() is not visible. Must explicitly invoke another constructor
class SubEnum2 extends SimulatedEnum2 {
	public SubEnum2() {
	}

	@Override
	public boolean foo() {
		return false;
	}
}

enum RealEnum2 implements Interface2 {
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

public class Example2 {
	public static void main(final String[] args) {
		Example2.simulatedEnums();
		System.out.println("\n***\n");
		Example2.realEnums();
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
}