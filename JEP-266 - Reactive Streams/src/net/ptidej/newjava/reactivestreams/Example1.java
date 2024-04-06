package net.ptidej.newjava.reactivestreams;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;

public class Example1 {
	public static class EndSubscriber<T> implements Subscriber<T> {
		private Subscription subscription;
		private final List<T> consumedElements = new LinkedList<>();

		@Override
		public void onSubscribe(final Subscription subscription) {
			this.subscription = subscription;
			subscription.request(1);
		}

		@Override
		public void onNext(final T item) {
			System.out.println("Got : " + item);
			consumedElements.add(item);
			subscription.request(1);
		}

		@Override
		public void onError(final Throwable t) {
			t.printStackTrace();
		}

		@Override
		public void onComplete() {
			System.out.println("Done");
		}
	}

	public static void main(final String[] args) {
		// Given
		final SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		final EndSubscriber<String> subscriber = new EndSubscriber<>();
		final List<String> items = List.of("1", "x", "2", "x", "3", "x");

		// When
		publisher.subscribe(subscriber);
		items.forEach(publisher::submit);
		publisher.close();

		// Then
		Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS)
				.until(() -> subscriber.consumedElements.containsAll(items));
	}
}

/*
Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(new Callable<Boolean>() {
 	@Override
	public Boolean call() throws Exception {
		return subscriber.consumedElements.containsAll(items);
	}
});
*/