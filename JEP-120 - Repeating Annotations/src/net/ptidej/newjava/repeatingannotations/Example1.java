// https://rolyhewage.medium.com/type-annotations-repeating-annotations-in-java-722073df9f99
package net.ptidej.newjava.repeatingannotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ScheduleNonRepeatable1 {
	String dayOfMonth() default "First";

	String dayOfWeek() default "Mon";

	int hour() default 12;
}

@ScheduleNonRepeatable1(dayOfWeek = "Fri", hour = 23)
public class Example1 {
	public static void main(final String[] args) {
		final Example1 example1 = new Example1();
		final Annotation annotation = example1.getClass().getAnnotation(ScheduleNonRepeatable1.class);
		System.out.println(annotation);
	}
}
