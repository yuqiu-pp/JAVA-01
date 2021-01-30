package io.howework.proxyBizFilter;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {

    public void filter(FullHttpRequest request);
}
