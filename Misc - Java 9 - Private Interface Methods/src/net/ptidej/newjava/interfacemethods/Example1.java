package net.ptidej.newjava.interfacemethods;

public class Example1 {
	public interface IA {
		int foo();

		default int bar() {
			return this.bar1();
		}

		private int bar1() {
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

		final IA anotherIA = new IA() {
			public int foo() {
				return IA.super.bar1();
			}

			public int bar() {
				return 0;
			}
		};
		System.out.println(anotherIA.foo());
		System.out.println(anotherIA.bar());
	}
}
