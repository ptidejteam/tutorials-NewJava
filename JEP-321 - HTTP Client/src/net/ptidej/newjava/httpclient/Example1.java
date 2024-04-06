package net.ptidej.newjava.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Example1 {
	public static void main(final String[] args) throws URISyntaxException, IOException, InterruptedException {
		final HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://postman-echo.com/get"))
				.version(HttpClient.Version.HTTP_2).timeout(Duration.ofSeconds(10)).header("key1", "value1")
				.header("key2", "value2").GET().build();
		final HttpClient client = HttpClient.newHttpClient();
		final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
	}
}
