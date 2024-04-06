package net.ptidej.newjava.interfacemethods;

public class Example2Static {
	public interface IA {
		int foo();

		static int bar() {
			return 42;
		}
	}

	public class A {
		int foo() {
			return 0;
		}

		static int bar() {
			return 42;
		}
	}

	public static void main(final String[] args) {
		final IA anIA = new IA() {
			public int foo() {
				return 0;
			}
		};
		System.out.println(anIA.foo());
		System.out.println(anIA.bar());
		System.out.println(IA.bar());

		final A anA = new A();
		System.out.println(anA.foo());
		System.out.println(anA.bar());
	}
}
