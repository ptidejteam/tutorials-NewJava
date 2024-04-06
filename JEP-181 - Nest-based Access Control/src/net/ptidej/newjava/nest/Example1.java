package net.ptidej.newjava.nest;

public class Example1 {
	private String name = "I'm Example1!";

	public class A {
		private String anotherName = "I'm Example1.A";

		public void printName() {
			System.out.println(name);
		}
	}

	public class B {
		public void printName() {
			Example1.A a = new A();
			System.out.println(a.anotherName);
		}

		public class B1 {
			public void printName() {
				Example1.B.this.printName();
				System.out.println(Example1.this.name);
			}
		}
	}

	public static void main(final String[] args) {
		final Example1 e1 = new Example1();
		final Example1.B b = e1.new B();
		final Example1.B.B1 b1 = b.new B1();
		b1.printName();
	}
}
