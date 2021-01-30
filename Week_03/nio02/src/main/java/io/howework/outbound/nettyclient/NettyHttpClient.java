package io.howework.outbound.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.URI;
import java.net.URISyntaxException;

public class NettyHttpClient {
    private int port;
    private String url;

    private static final EventLoopGroup workerGroup = new NioEventLoopGroup();
    //启动类
    // private static final Bootstrap b = new Bootstrap();

    public NettyHttpClient(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public void start() throws InterruptedException {
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new NettyHttpClientInitializer());

            // host
            // ChannelFuture f = b.connect(getHost(url), port).sync();
            ChannelFuture f = b.connect("127.0.0.1", port).sync();

            f.channel().closeFuture().sync();

            // f.channel().write(request);
           // f.channel().flush();
           // f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    private String getHost(String url) {
        try {
            URI uri = new URI(url);
            return uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        NettyHttpClient client = new NettyHttpClient("http://127.0.0.1", 8801);
        client.start();
    }
}
