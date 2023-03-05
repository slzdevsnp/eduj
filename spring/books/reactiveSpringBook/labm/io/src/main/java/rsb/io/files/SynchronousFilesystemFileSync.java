package rsb.io.files;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.function.Consumer;

/**
 * This class is synchronous and blocking: it reads data and blocks the client thread. It
 * is possible to make this code asynchronous, but we would need to explicitly involve
 * threading somewhere.
 *
 * @author Josh Long
 */
class SynchronousFilesystemFileSync implements FilesystemFileSync {

	@Override
	@SneakyThrows
	public void start(File source, Consumer<byte[]> consumer) {

		try (//
				var in = new BufferedInputStream(new FileInputStream(source)); //
				var out = new ByteArrayOutputStream() //
		) {
			var read = -1;
			var bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			consumer.accept(out.toByteArray());
		}
	}

}
