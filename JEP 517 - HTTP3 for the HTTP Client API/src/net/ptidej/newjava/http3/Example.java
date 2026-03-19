// https://openjdk.org/jeps/517

package net.ptidej.newjava.http3;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Example {

	public static void main(final String... args)
			throws IOException, InterruptedException {

		// HTTP/1.1 or HTTP/2
		// ------------------
		// Version 1
		{
			var client = HttpClient.newHttpClient();
			var request = HttpRequest
					.newBuilder(URI.create("https://openjdk.org/")).GET()
					.build();
			var response = client.send(request,
					HttpResponse.BodyHandlers.ofString());
			assert response.statusCode() == 200;
			final String htmlText = response.body();
			System.out.println(htmlText);
		}
		// Version 2
		{
			var client = HttpClient.newBuilder().build();
			var request = HttpRequest
					.newBuilder(URI.create("https://openjdk.org/")).GET()
					.build();
			var response = client.send(request,
					HttpResponse.BodyHandlers.ofString());
			assert response.statusCode() == 200;
			final String htmlText = response.body();
			System.out.println(htmlText);
		}

		// HTTP/3
		// ------
		// At the client-level
		{
			var client = HttpClient.newBuilder()
					.version(HttpClient.Version.HTTP_3).build();
			var request = HttpRequest
					.newBuilder(URI.create("https://openjdk.org/")).GET()
					.build();
			var response = client.send(request,
					HttpResponse.BodyHandlers.ofString());
			assert response.statusCode() == 200;
			final String htmlText = response.body();
			System.out.println(htmlText);
		}

		// At the request-level
		{
			var client = HttpClient.newBuilder().build();
			var request = HttpRequest
					.newBuilder(URI.create("https://openjdk.org/"))
					.version(HttpClient.Version.HTTP_3).GET().build();
			var response = client.send(request,
					HttpResponse.BodyHandlers.ofString());
			assert response.statusCode() == 200;
			final String htmlText = response.body();
			System.out.println(htmlText);
		}

	}

}