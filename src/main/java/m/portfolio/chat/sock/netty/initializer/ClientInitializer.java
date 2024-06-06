package m.portfolio.chat.sock.netty.initializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import m.portfolio.chat.sock.netty.handler.ClientHandler;
import org.springframework.stereotype.Component;

@Component
public class ClientInitializer extends BaseSocketInitializer {
    private EventLoopGroup group = null;

    @Override
    public void start() throws InterruptedException {
        this.group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LengthFieldBasedFrameDecoder(
                                Integer.MAX_VALUE, 0, 4, 0, 4
                        ));
                        p.addLast(new ClientHandler());
//                        p.addLast(new LengthFieldPrepender(4));
                    }
                });

        ChannelFuture f = b.connect("localhost", 8888).sync();

        f.channel().closeFuture().sync();
    }

    @Override
    public void shutdown() {
        this.group.shutdownGracefully();
    }
}