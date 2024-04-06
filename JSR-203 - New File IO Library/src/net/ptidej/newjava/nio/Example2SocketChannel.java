// https://www.tutorialspoint.com/java_nio/java_nio_socket_channel.htm
package net.ptidej.newjava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example2SocketChannel {
	public static void main(final String[] args) {
		final Runnable runnableClient = new Runnable() {
			@Override
			public void run() {
				try {
					Example2SocketChannel.client();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		};

		final Runnable runnableServer = new Runnable() {
			@Override
			public void run() {
				try {
					Example2SocketChannel.server();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		};

		final ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(runnableClient);
		executor.execute(runnableServer);
	}

	public static void client() throws IOException {
		final ServerSocketChannel serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(new InetSocketAddress(9000));
		final SocketChannel client = serverSocket.accept();
		System.out.println("Connection set: " + client.getRemoteAddress());

		final Path path = Paths.get("D:\\Documents\\Tutorials\\220926 - New Java\\ReceivedFile.txt");
		final FileChannel fileChannel = FileChannel.open(path,
				EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE));
		final ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (client.read(buffer) > 0) {
			buffer.flip();
			fileChannel.write(buffer);
			buffer.clear();
		}
		fileChannel.close();
		System.out.println("File received");
		client.close();
	}

	public static void server() throws IOException {
		final SocketChannel server = SocketChannel.open();
		final SocketAddress socketAddr = new InetSocketAddress("localhost", 9000);
		server.connect(socketAddr);

		final Path path = Paths.get("D:\\\\Documents\\\\Tutorials\\\\220926 - New Java\\\\Patterns.txt");
		final FileChannel fileChannel = FileChannel.open(path);
		final ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (fileChannel.read(buffer) > 0) {
			buffer.flip();
			server.write(buffer);
			buffer.clear();
		}
		fileChannel.close();
		System.out.println("File sent");
		server.close();
	}
}