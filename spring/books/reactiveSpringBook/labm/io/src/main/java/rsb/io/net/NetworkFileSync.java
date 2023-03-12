package rsb.io.net;

import java.util.function.Consumer;

/**
 * Receives inbound files and lets the consumer handle them in some way.
 * <p>
 * To test, run an implementation and try something like:
 *
 * <pre>{@code
 *   nc 127.0.0.1 8888 < ~/code/reactive-spring-book/io/content
 * }</pre>
 */
interface NetworkFileSync {

	void start(int port, Consumer<byte[]> bytesHandler);

}
