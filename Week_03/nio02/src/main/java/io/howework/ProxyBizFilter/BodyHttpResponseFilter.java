package io.howework.proxyBizFilter;

import io.netty.handler.codec.http.FullHttpResponse;

public class BodyHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        // ByteBuf buf = response..content();
        // String body = buf.toString(io.netty.util.CharsetUtil.UTF_8);
        // body += " - filter";
        // content.replace(Unpooled.wrappedBuffer(body.getBytes(io.netty.util.CharsetUtil.UTF_8)));
        // System.out.println(content);
    }
}
