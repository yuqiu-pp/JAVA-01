package io.howework.outbound.nettyclient;

import io.howework.ProxyBizFilter.BodyHttpResponseFilter;
import io.howework.ProxyBizFilter.HeaderHttpResponseFilter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

// client 首先是收到要转发的请求，所以这里是ChannelInboundHandlerAdapter
public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter{

    private static BodyHttpResponseFilter bodyHttpResponseFilter = new BodyHttpResponseFilter();
    private static HeaderHttpResponseFilter headerHttpResponseFilter = new HeaderHttpResponseFilter();

    public void nettyHttpHandler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        // 生成路由新url
        String url = "http://127.0.0.1:8801" + fullHttpRequest.getUri();
        String port = "8801";

        // 过滤request

        // 转发request
        fetchGet(fullHttpRequest, ctx, url);
    }

    public void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String url) {
        // 发送请求
        // url = url.charAt(url.length() - 1) == '\\' ? url.substring(0, url.length() - 2) : url;
        url = url.substring(0, url.length() - 2);
        NettyHttpClient client = new NettyHttpClient(url, 8801);
        try {
            client.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // public void handlerResponse(String body, ChannelHandlerContext ctx) {
    //     FullHttpResponse fullHttpResponse = null;
    //     if (body == null) {
    //         fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
    //     } else {
    //         fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body.getBytes()));
    //     }
    //
    //     fullHttpResponse.headers().set("Content-Type", "application/json");
    //     fullHttpResponse.headers().setInt("Content-Length", body.length());
    //     // 返回给前端
    //     ctx.writeAndFlush(fullHttpResponse);
    // }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    // 转发request后的response在这里收到
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead");
        // bodyHttpResponseFilter.filter();
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            // headerHttpResponseFilter.filter(response);
            ctx.write(response);
        }
        ctx.flush();

        // if (msg instanceof HttpResponse) {
        //     HttpResponse response = (HttpResponse) msg;
        //     System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
        // }
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            String cont = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            System.out.println(cont);
            // ctx.writeAndFlush(cont);
            // buf.release();
        }


    }

    // @Override
    // public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    //     super.channelReadComplete(ctx);
    // }
}
