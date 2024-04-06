// class Example3 {
//	public static void main(final String[] args) {
//		final Example3 o = new Example3();
//		o.main();
//	}

	void main() {
		System.out.println("Hello, World!");
		final Class clazz = this.getClass();
		System.out.println(clazz.getName());
		System.out.println(clazz.getPackage().getName());
		System.out.println(clazz.getModule().getName());
	}
// }