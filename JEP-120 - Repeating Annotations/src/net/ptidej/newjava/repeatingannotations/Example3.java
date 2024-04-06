// https://rolyhewage.medium.com/type-annotations-repeating-annotations-in-java-722073df9f99
package net.ptidej.newjava.repeatingannotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Schedules {
	ScheduleRepeatable[] value();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Schedules.class)
@interface ScheduleRepeatable {
	String dayOfMonth() default "First";

	String dayOfWeek() default "Mon";

	int hour() default 12;
}

@ScheduleRepeatable(dayOfMonth = "last")
@ScheduleRepeatable(dayOfWeek = "Fri", hour = 23)
public class Example3 {
	public static void main(final String[] args) {
		final Example3 example3 = new Example3();
		final Annotation annotation = example3.getClass().getAnnotation(Schedules.class);
		System.out.println(annotation);
	}
}
