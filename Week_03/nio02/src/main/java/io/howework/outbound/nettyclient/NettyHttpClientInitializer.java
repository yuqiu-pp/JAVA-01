package io.howework.outbound.nettyclient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;


public class NettyHttpClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpResponseDecoder());
        p.addLast(new HttpRequestEncoder());
        // 把第一步传给我们的httpRequest，编码为bytebuf，交给channel发送
        // p.addLast(new HttpClientCodec());
        // 将bytebuf，转变为io.netty.handler.codec.http.FullHttpResponse 类型的对象
        // p.addLast(new HttpObjectAggregator(1024 * 1024));
        // p.addLast(new HttpContentDecompressor());
        // p.addLast(new NettyHttpClientOutHandler());
        p.addLast(new NettyHttpClientOutboundHandler());
    }
}
