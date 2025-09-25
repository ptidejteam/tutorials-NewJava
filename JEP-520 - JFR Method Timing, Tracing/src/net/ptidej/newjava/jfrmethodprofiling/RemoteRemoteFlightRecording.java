package net.ptidej.newjava.jfrmethodprofiling;

import java.util.HashMap;

import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RemoteRemoteFlightRecording {
	public static void main(final String[] args) {
		// Establish non-secure connection to remote host
		var url = "service:jmx:rmi:///jndi/rmi://example.com:7091/jmxrmi";
		var jmxURL = new JMXServiceURL(url);
		try (var conn = JMXConnectorFactory.connect(jmxURL)) {
			try (var r = new RemoteRecordingStream(
					conn.getMBeanServerConnection())) {

				// Create map for settings
				var settings = new HashMap<String, String>();

				// Trace methods in class com.foo.Bar that take more than 1 ms to execute
				settings.put("jdk.MethodTrace#enabled", "true");
				settings.put("jdk.MethodTrace#stackTrace", "true");
				settings.put("jdk.MethodTrace#threshold", "1 ms");
				settings.put("jdk.MethodTrace#filter", "com.foo.Bar");

				// Subscribe to trace events
				r.onEvent("jdk.MethodTrace", event -> ...);

				// Measure execution time and invocation count for methods with
				// the jakarta.ws.rs.GET annotation, and emit the result every 10 s
				settings.put("jdk.MethodTiming#enabled", "true");
				settings.put("jdk.MethodTiming#filter", "@jakarta.ws.rs.GET");
				settings.put("jdk.MethodTiming#period", "10 s");

				// Subscribe to timing events
				r.onEvent("jdk.MethodTiming", event -> ...);

				// Set the settings, then time and trace for 60 seconds
				r.setSettings(settings);
				r.startAsync();
				Thread.sleep(60_000);
				r.stop();
			}
		}
	}
}
