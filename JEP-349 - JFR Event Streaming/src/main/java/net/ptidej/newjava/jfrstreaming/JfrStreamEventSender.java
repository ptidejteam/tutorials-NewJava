package net.ptidej.newjava.jfrstreaming;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.jfr.consumer.RecordedEvent;

public final class JfrStreamEventSender implements Consumer<RecordedEvent> {
	private static final String SERVER_URL = "http://127.0.0.1:8080/events";

	@Override
	public void accept(final RecordedEvent event) {
		try {
			// var payload = FileProcessor.decodeEvent(event);
			System.out.println(event);
			String json = new ObjectMapper().writeValueAsString(event);

			var client = HttpClient.newHttpClient();

			var request = HttpRequest.newBuilder().uri(URI.create(SERVER_URL)).timeout(Duration.ofSeconds(30))
					.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			Logger.getLogger("JfrStreamEventSender").log(Level.INFO,
					"Server response code: " + response.statusCode() + ", body: " + response.body());
		} catch (IOException | InterruptedException e) {
			Logger.getLogger("JfrStreamEventSender").log(Level.SEVERE, "Unable to send JFR event to server", e);
		}
	}
}