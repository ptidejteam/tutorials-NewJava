// https://gee.cs.oswego.edu/dl/concurrency-interest/jsr166-slides.pdf
package net.ptidej.newjava.concurrency;

import java.awt.Image;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ImageRenderer {
	Image render(final byte[] raw) {
		return null;
	}
}

public class Example2Futures {
	public void display(final byte[] raw) throws InterruptedException, ExecutionException {
		final ExecutorService executor = Executors.newFixedThreadPool(10);
		final ImageRenderer renderer = new ImageRenderer();
		final Future<Image> image = executor.submit(new Callable<Image>() {
			public Image call() {
				return renderer.render(raw);
			}
		});
		drawBorders(); // do other things ...
		drawCaption(); // ... while executing
		drawImage(image.get()); // use Future
	}

	// ...

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new Example2Futures().display(null);
	}

	private void drawImage(final Image anImage) {
		// ...
	}

	private void drawCaption() {
		// ...
	}

	private void drawBorders() {
		// ...
	}
}
