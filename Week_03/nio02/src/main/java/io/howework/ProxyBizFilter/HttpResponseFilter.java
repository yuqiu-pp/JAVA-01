package io.howework.proxyBizFilter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpResponseFilter {

    public void filter(FullHttpResponse response);

}
