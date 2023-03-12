package rsb.io.net;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Reads data in a non-blocking and asynchronous fashion
 */
@Slf4j
class NioNetworkFileSync implements NetworkFileSync {

	public static void main(String[] args) throws Exception {
		var nfs = new NioNetworkFileSync();
		nfs.start(8888, new FileSystemPersistingByteConsumer("nio"));
	}

	@Override
	@SneakyThrows
	public void start(int port, Consumer<byte[]> bytesHandler) {

		var serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port));

		var selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (!Thread.currentThread().isInterrupted()) {
			selector.select();
			var selectionKeys = selector.selectedKeys();
			for (var it = selectionKeys.iterator(); it.hasNext();) {
				var key = it.next();
				it.remove();
				if (key.isAcceptable()) {
					var socket = serverSocketChannel.accept();
					accept(key, selector, socket);
				} //
				else if (key.isReadable()) {
					read(key, selector, bytesHandler);
				}
			}
		}

	}

	record ReadAttachment(SelectionKey key, List<ByteBuffer> buffers) {
	}

	@SneakyThrows
	private static void saveFile(List<ByteBuffer> buffers, Consumer<byte[]> handler) {

		try (var baos = new ByteArrayOutputStream()) {
			for (var bb : buffers) {
				bb.flip();
				var bytes = new byte[bb.limit()];
				bb.get(bytes);
				baos.write(bytes, 0, bb.position());
			}
			var bytes = baos.toByteArray();
			handler.accept(bytes);

		}
	}

	private static void read(SelectionKey key, Selector selector, Consumer<byte[]> handler) throws Exception {
		var ra = (ReadAttachment) key.attachment();
		var len = 1000;
		var bb = ByteBuffer.allocate(len);
		var channel = (SocketChannel) key.channel();
		var read = -1;

		if ((read = channel.read(bb)) >= 0) {
			ra.buffers().add(bb);
			channel.register(selector, SelectionKey.OP_READ, new ReadAttachment(ra.key(), ra.buffers()));
		}

		if (read == -1) {
			saveFile(ra.buffers(), handler);
			channel.register(selector, SelectionKey.OP_WRITE);
		}
	}

	@SneakyThrows
	private static void accept(SelectionKey key, Selector selector, SocketChannel socketChannel) {
		var readAttachment = new ReadAttachment(key, new CopyOnWriteArrayList<>());
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ, readAttachment);
	}

}
