package io.howework.ProxyBizFilter;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {

    public void filter(FullHttpRequest request);
}
