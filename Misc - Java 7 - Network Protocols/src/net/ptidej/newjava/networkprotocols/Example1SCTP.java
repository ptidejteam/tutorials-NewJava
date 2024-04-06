// https://www.oracle.com/technical-resources/articles/javase/sctp.html
package net.ptidej.newjava.networkprotocols;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Example1SCTP {
	static int SERVER_PORT = 3456;
	static int US_STREAM = 0;
	static int FR_STREAM = 1;

	static SimpleDateFormat USformatter = new SimpleDateFormat("h:mm:ss a EEE d MMM yy, zzzz", Locale.US);
	static SimpleDateFormat FRformatter = new SimpleDateFormat("h:mm:ss a EEE d MMM yy, zzzz", Locale.FRENCH);

	public static void main(String[] args) throws IOException {
		final ByteBuffer buf = ByteBuffer.allocateDirect(60);
		final CharBuffer cbuf = CharBuffer.allocate(60);
		final Charset charset = Charset.forName("ISO-8859-1");
		final CharsetEncoder encoder = charset.newEncoder();

		final SctpServerChannel ssc = SctpServerChannel.open();
		final InetSocketAddress serverAddr = new InetSocketAddress(SERVER_PORT);
		ssc.bind(serverAddr);

		while (true) {
			final SctpChannel sc = ssc.accept();
			final Date today = new Date();

			cbuf.put(USformatter.format(today)).flip();
			encoder.encode(cbuf, buf, true);
			buf.flip();
			final MessageInfo messageInfo = MessageInfo.createOutgoing(null, US_STREAM);
			sc.send(buf, messageInfo);
			buf.clear();
			cbuf.clear();

			cbuf.put(FRformatter.format(today)).flip();
			encoder.encode(cbuf, buf, true);
			buf.flip();
			messageInfo.streamNumber(FR_STREAM);
			sc.send(buf, messageInfo);
			buf.clear();
			cbuf.clear();
			
			// ...

			sc.close();
		}
	}
}
