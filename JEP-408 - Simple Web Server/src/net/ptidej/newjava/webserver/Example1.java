// https://www.baeldung.com/simple-web-server-java-18
package net.ptidej.newjava.webserver;

import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.function.Predicate;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpHandlers;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Request;
import com.sun.net.httpserver.SimpleFileServer;

public class Example1 {
	public static void main(final String[] args) {
		final InetSocketAddress address = new InetSocketAddress(8080);
		final Path path1 = Path.of("D:\\Documents\\Tutorials\\220926 - New Java");
		final HttpServer server = SimpleFileServer.createFileServer(address, path1,
				SimpleFileServer.OutputLevel.VERBOSE);

		final Path path2 = Path.of("D:\\Documents\\Tutorials\\220926 - New Java\\Workspace");
		final HttpHandler handler1 = SimpleFileServer.createFileHandler(path2);
		server.createContext("/test1", handler1);

		final HttpHandler allowedResponse = HttpHandlers.of(200, Headers.of("Allow", "GET"), "Welcome");
		final HttpHandler deniedResponse = HttpHandlers.of(401, Headers.of("Deny", "GET"), "Denied");
		final Predicate<Request> findAllowedPath = r -> r.getRequestURI().getPath().equals("/test2/allowed");
		final HttpHandler handler2 = HttpHandlers.handleOrElse(findAllowedPath, allowedResponse, deniedResponse);
		server.createContext("/test2", handler2);

		server.start();
	}
}
