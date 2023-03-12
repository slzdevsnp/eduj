package rsb.io.files;

import java.io.File;
import java.util.function.Consumer;

interface FilesystemFileSync {

	void start(File source, Consumer<byte[]> handler);

}
