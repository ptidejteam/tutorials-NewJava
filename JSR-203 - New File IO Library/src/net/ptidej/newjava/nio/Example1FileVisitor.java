// https://www.baeldung.com/java-nio2-file-visitor
package net.ptidej.newjava.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

class FileVisitorImpl implements FileVisitor<Path> {
	private final String fileName;
	private final Path startPath;

	public FileVisitorImpl(final String aFileName, final Path aStartPath) {
		this.fileName = aFileName;
		this.startPath = aStartPath;
	}

	@Override
	public FileVisitResult preVisitDirectory(final Path aPath, final BasicFileAttributes someAttributes) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(final Path aPath, final BasicFileAttributes someAttributes) {
		final String fileName = aPath.getFileName().toString();
		if (fileName.equals(this.fileName)) {
			System.out.println("File found: " + fileName);
			return FileVisitResult.TERMINATE;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(final Path aPath, final IOException anIOException) {
		System.out.println("Failed to access file: " + aPath.toString());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(final Path aPath, final IOException anIOException) {
		try {
			final boolean finishedSearch = Files.isSameFile(aPath, this.startPath);
			if (finishedSearch) {
				System.out.println("File: " + this.fileName + " not found");
				return FileVisitResult.TERMINATE;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return FileVisitResult.CONTINUE;
	}
}

public class Example1FileVisitor {
	public static void main(final String[] args) throws IOException {
		final Path startPath = Paths.get("D:\\Documents\\Tutorials\\220926 - New Java");
		final String fileName = "Example1FileVisitor.java";
		final FileVisitorImpl visitor = new FileVisitorImpl(fileName, startPath);
		Files.walkFileTree(startPath, visitor);
	}
}
