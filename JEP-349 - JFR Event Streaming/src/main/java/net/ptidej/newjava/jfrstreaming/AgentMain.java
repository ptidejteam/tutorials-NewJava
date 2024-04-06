package net.ptidej.newjava.jfrstreaming;

import java.lang.instrument.Instrumentation;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import jdk.jfr.consumer.RecordingStream;

public class AgentMain implements Runnable {
	public static void premain(String agentArgs, Instrumentation inst) {
		try {
			Logger.getLogger("AgentMain").log(Level.INFO, "Attaching JFR Monitor");
			new Thread(new AgentMain()).start();
		} catch (Throwable t) {
			Logger.getLogger("AgentMain").log(Level.SEVERE, "Unable to attach JFR Monitor", t);
		}
	}

	public void run() {
		var sender = new JfrStreamEventSender();
		try (var rs = new RecordingStream()) {
			rs.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
			rs.enable("jdk.JavaMonitorEnter").withThreshold(Duration.ofMillis(10));
			rs.onEvent("jdk.CPULoad", sender);
			rs.onEvent("jdk.JavaMonitorEnter", sender);
			rs.start();
		}
	}
}
