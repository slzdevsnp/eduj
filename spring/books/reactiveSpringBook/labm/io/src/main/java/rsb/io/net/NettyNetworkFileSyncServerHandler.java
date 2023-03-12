package rsb.io.net;

import io.netty.buffer.AbstractReferenceCountedByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Slf4j
@ChannelHandler.Sharable
class NettyNetworkFileSyncServerHandler extends ChannelInboundHandlerAdapter {

	private final Consumer<byte[]> consumer;

	private final AtomicReference<ByteArrayOutputStream> byteArrayOutputStream = new AtomicReference<>(
			new ByteArrayOutputStream());

	NettyNetworkFileSyncServerHandler(Consumer<byte[]> consumer) {
		this.consumer = consumer;
	}

	@Override
	@SneakyThrows
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		if (msg instanceof AbstractReferenceCountedByteBuf buf) {
			var bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			this.byteArrayOutputStream.get().write(bytes);
		}
	}

	@SneakyThrows
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		var baos = this.byteArrayOutputStream.get();
		if (null != baos) {
			try {
				var bytes = baos.toByteArray();
				if (bytes.length != 0) {
					this.consumer.accept(bytes);
				}
				// we've read the bytes,
				// time to reset for a new request
				this.byteArrayOutputStream.set(new ByteArrayOutputStream());
			} //
			finally {
				ctx.flush();
				baos.close();
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.error("oh no!", cause);
		ctx.close();
	}

}
