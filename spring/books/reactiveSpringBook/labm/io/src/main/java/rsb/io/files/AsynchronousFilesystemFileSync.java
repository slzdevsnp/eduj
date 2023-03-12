package rsb.io.files;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
class AsynchronousFilesystemFileSync implements FilesystemFileSync {

	@Override
	@SneakyThrows
	public void start(File source, Consumer<byte[]> handler) {
		// <1>
		var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		// <2>
		var fileChannel = AsynchronousFileChannel.open(source.toPath(), Collections.singleton(StandardOpenOption.READ),
				executorService);
		// <3>
		var completionHandler = new AsynchronousFileCompletionHandler(executorService, handler, source, fileChannel);
		var attachment = new AsynchronousReadAttachment(source, ByteBuffer.allocate(1024), new ByteArrayOutputStream(),
				0);
		fileChannel.read(attachment.buffer(), attachment.position(), attachment, completionHandler);
	}

}
