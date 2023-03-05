package rsb.io.files;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
class AsynchronousFileCompletionHandler implements CompletionHandler<Integer, AsynchronousReadAttachment> {

	private final ExecutorService executorService;

	private final Consumer<byte[]> handler;

	private final File source;

	private final AsynchronousFileChannel fileChannel;

	@Override
	@SneakyThrows
	public void completed(Integer result, AsynchronousReadAttachment attachment) {
		var byteArrayOutputStream = attachment.byteArrayOutputStream();
		if (!result.equals(-1)) {
			var buffer = attachment.buffer();
			buffer.flip();
			var storage = new byte[buffer.limit()];
			buffer.get(storage);
			byteArrayOutputStream.write(storage);
			attachment.buffer().clear();
			var ra = new AsynchronousReadAttachment(source, attachment.buffer(), //
					byteArrayOutputStream, //
					attachment.position() + attachment.buffer().limit() //
			);
			fileChannel.read(attachment.buffer(), ra.position(), ra, this);
		} //
		else { // it's -1
			var all = byteArrayOutputStream.toByteArray();
			try {
				byteArrayOutputStream.close();
				executorService.shutdown();
			} //
			catch (Exception e) {
				error(e, attachment);
			}
			handler.accept(all);

		}
	}

	@Override
	public void failed(Throwable throwable, AsynchronousReadAttachment attachment) {
		error(throwable, attachment);
	}

	private static void error(Throwable throwable, AsynchronousReadAttachment attachment) {
		log.error("error reading file '" + attachment.source().getAbsolutePath() + "'!", throwable);
	}

}
