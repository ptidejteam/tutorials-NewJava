package net.ptidej.newjava.hiddenclasses;

import java.util.concurrent.Callable;

class TopLevelClass {
	public class MemberClass {
	}
}

public class Example1 {
	public static void main(final String[] args) throws ClassNotFoundException {
		class InnerClass {
		}
		final InnerClass innerClass = new InnerClass();

		final Callable<String> anonynmousClass = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return null;
			}
		};

		final Callable<String> lambdaExpression = () -> null;

		final Class<?> classOfClazz = Class.forName("net.ptidej.newjava.hiddenclasses.TopLevelClass");
		InfoPrinter.printInfo(classOfClazz);

		final Class<?> classOfMemberClass = Class.forName("net.ptidej.newjava.hiddenclasses.TopLevelClass$MemberClass");
		InfoPrinter.printInfo(classOfMemberClass);

		final Class<?> classOfInnerClass = innerClass.getClass();
		// final Class<?> classOfInnerClass = Class.forName("net.ptidej.newjava.hiddenclasses.Example1$1InnerClass");
		InfoPrinter.printInfo(classOfInnerClass);

		final Class<?> classOfAnonymousClass = anonynmousClass.getClass();
		// final Class<?> classOfAnonymousClass = Class.forName("net.ptidej.newjava.hiddenclasses.Example1$1");
		InfoPrinter.printInfo(classOfAnonymousClass);

		final Class<?> classOfLambdaExpression = lambdaExpression.getClass();
		// final Class<?> classOfLambdaExpression = Class.forName("net.ptidej.newjava.hiddenclasses.Example1$$Lambda/???");
		InfoPrinter.printInfo(classOfLambdaExpression);
	}
}
