package rsb.io.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.AbstractEventExecutorGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
class NettyNetworkFileSync implements NetworkFileSync {

	public static void main(String[] args) throws Exception {
		var nfs = new NettyNetworkFileSync();
		nfs.start(8888, new FileSystemPersistingByteConsumer("netty"));
	}

	@Override
	@SneakyThrows
	public void start(int port, Consumer<byte[]> bytesHandler) {

		var bossEventLoopGroup = new NioEventLoopGroup(1);
		var nioEventLoopGroup = new NioEventLoopGroup();
		var serverHandler = new NettyNetworkFileSyncServerHandler(bytesHandler);
		try {
			var serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossEventLoopGroup, nioEventLoopGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						public void initChannel(SocketChannel ch) {
							var channelPipeline = ch.pipeline();
							channelPipeline.addLast(serverHandler);
						}
					});
			var channelFuture = serverBootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} //
		finally {
			shutdown(List.of(bossEventLoopGroup, nioEventLoopGroup));
		}
	}

	private static void shutdown(List<NioEventLoopGroup> groups) {
		groups.forEach(AbstractEventExecutorGroup::shutdownGracefully);
	}

}
