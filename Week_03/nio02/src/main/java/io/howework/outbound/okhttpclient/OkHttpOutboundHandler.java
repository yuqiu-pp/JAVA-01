package io.howework.outbound.okhttpclient;


import io.howework.proxyBizFilter.HeaderHttpRequestFilter;
import io.howework.proxyBizFilter.HeaderHttpResponseFilter;
import io.howework.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkHttpOutboundHandler {
       // public static OkHttpClient client = new OkHttpClient();
       public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

       public static HeaderHttpResponseFilter headerHttpResponseFilter = new HeaderHttpResponseFilter();
       public static RandomHttpEndpointRouter randomHttpEndpointRouter = new RandomHttpEndpointRouter();
       private static HeaderHttpRequestFilter headerHttpRequestFilter = new HeaderHttpRequestFilter();

       public void okHttpHandler(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, List<String> proxyServer) {
           // 组路由新url
           // String url = "http://127.0.0.1:8801" + fullHttpRequest.getUri();
           String url = getServerAddr(proxyServer) + fullHttpRequest.uri();

           // request filter
            headerHttpRequestFilter.filter(fullHttpRequest);

           // 作为cline请求http server
           fetchGet(fullHttpRequest, ctx, url);
       }

       public String getServerAddr(List<String> proxyServer) {
            return randomHttpEndpointRouter.router(proxyServer);
       }

       public void fetchGet(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String url) {
           // 发送请求
           OkHttpClient client = new OkHttpClient();
           Request request = new Request.Builder()
                   .url(url)
                   .build();
           String body = null;
           try {
               body = client.newCall(request).execute().body().string();
           } catch (IOException e) {
               e.printStackTrace();
           }

           // 处理response
           handlerResponse(body, ctx);
       }

       public void handlerResponse(String body, ChannelHandlerContext ctx) {
           FullHttpResponse fullHttpResponse = null;
           if (body == null) {
               fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
           } else {
               fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body.getBytes()));
           }

           fullHttpResponse.headers().set("Content-Type", "application/json");
           fullHttpResponse.headers().setInt("Content-Length", body.length());

           headerHttpResponseFilter.filter(fullHttpResponse);

           // 返回给前端
           ctx.write(fullHttpResponse);
       }



       public static String getRequest(String url) throws IOException {
           OkHttpClient client = new OkHttpClient();
           Request request = new Request.Builder()
                   .url(url)
                   .build();

           try (Response response = client.newCall(request).execute()) {
               return response.body().string();
           }
       }
       

       public static String postRequest(String url, String json) throws IOException {
           OkHttpClient client = new OkHttpClient();
           RequestBody body = RequestBody.create(JSON, json);
           Request request = new Request.Builder()
                   .url(url)
                   .post(body)
                   .build();
           try (Response response = client.newCall(request).execute()) {
               return response.body().string();
           }
       }


       public static void main(String[] args) throws IOException {
           // String url = "https://square.github.io/okhttp/";
           String url = "http://localhost:8803";
           String text = OkHttpOutboundHandler.getRequest(url);
           System.out.println("url: " + url);
           System.out.println("response: " + text);

           // OkHttpOutboundHandler.client = null;
       }
}
