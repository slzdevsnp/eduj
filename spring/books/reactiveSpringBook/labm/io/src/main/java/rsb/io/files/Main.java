package rsb.io.files;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Slf4j
public class Main {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	Executor executor() {
		return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}

	// <1>
	@Bean
	@SneakyThrows
	ApplicationRunner runner(Map<String, FilesystemFileSync> filesystemFileSyncMap, Executor executor) {
		var file = Files//
				.createTempFile("io-content-data", ".txt")//
				.toFile();
		file.deleteOnExit();
		try (var in = Main.class.getResourceAsStream("/content"); var out = new FileOutputStream(file)) {
			FileCopyUtils.copy(in, out);
		}
		log.info("file.length: " + file.length());

		return args -> filesystemFileSyncMap.forEach((beanName, fss) -> {
			var classSimpleName = fss.getClass().getSimpleName().toLowerCase(Locale.ROOT);
			log.info("running " + classSimpleName);
			// <2>
			executor.execute(() -> fss.start(file, new BytesConsumer(file, beanName)));
		});
	}

	@RequiredArgsConstructor
	static class BytesConsumer implements Consumer<byte[]> {

		private final File source;

		private final String prefix;

		@Override
		public void accept(byte[] bytes) {
			log.info(prefix + ':' + bytes.length + ':' + source.getAbsolutePath());
		}

	}

}
