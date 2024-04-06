package net.ptidej.newjava.nest;

public class Example2 {
	private String name = "I'm Example2!";

	public class A {
		public void printName() {
			System.out.println(name);
		}
	}

	public class B {
		public void printName() {
			System.out.println(Example2.this.name);
		}

		public class B1 {
			public void printName() {
				System.out.println(Example2.this.name);
			}
		}
	}

	public static void main(final String[] args) {
		System.out.println(A.class.getNestHost());
		System.out.println(A.class.isNestmateOf(B.class));
		for (final Class<?> clazz : Example2.B.class.getNestMembers()) {
			System.out.println(clazz);
		}
	}
}
