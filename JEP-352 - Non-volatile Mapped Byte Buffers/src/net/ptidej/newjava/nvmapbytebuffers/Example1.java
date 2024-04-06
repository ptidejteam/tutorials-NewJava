// https://mashona.io/blog/2020/03/17/using-jep-352-api
package net.ptidej.newjava.nvmapbytebuffers;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Example1 {
	public static void main(final String[] args) throws IOException {
		final File file = new File("/path/to/some/file");
		final FileChannel fileChannel = (FileChannel) Files.newByteChannel(file.toPath(),
				EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE));

		int positionInFile = 0;
		int lengtfOfData = 255;

		try {
			final MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, positionInFile, lengtfOfData);
			System.out.println(mappedByteBuffer);
			// ...
		} catch (final IOException e) {
			// ...
		}
	}
}
