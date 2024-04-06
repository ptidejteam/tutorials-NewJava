// https://rolyhewage.medium.com/type-annotations-repeating-annotations-in-java-722073df9f99
package net.ptidej.newjava.repeatingannotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ScheduleNonRepeatable2 {
	String dayOfMonth() default "First";

	String dayOfWeek() default "Mon";

	int hour() default 12;
}

@ScheduleNonRepeatable2(dayOfMonth = "last")
@ScheduleNonRepeatable2(dayOfWeek = "Fri", hour = 23)
public class Example2 {
	public static void main(final String[] args) {
		final Example2 example2 = new Example2();
		final Annotation annotation = example2.getClass().getAnnotation(ScheduleNonRepeatable2.class);
		System.out.println(annotation);
	}
}
