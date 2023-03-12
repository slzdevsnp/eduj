package rsb.io.net;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.function.Consumer;

@Slf4j
record FileSystemPersistingByteConsumer(String prefix) implements Consumer<byte[]> {

	@Override
	@SneakyThrows
	public void accept(byte[] bytes) {
		log.info("the bytes length is " + bytes.length);
		var outputDirectory = new File(new File(System.getenv("HOME"), "Desktop"), "output");
		Assert.isTrue(outputDirectory.mkdirs() || outputDirectory.exists(),
				() -> "the folder " + outputDirectory.getAbsolutePath() + " does not exist");
		var file = new File(outputDirectory, prefix + ".download");
		FileCopyUtils.copy(bytes, new FileOutputStream(file));
	}

}
