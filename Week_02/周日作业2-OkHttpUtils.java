package com.nio1.client;


import okhttp3.*;

import java.io.IOException;

public class OkHttpUtils {
       public static OkHttpClient client = new OkHttpClient();
       public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

       public static String getRequest(String url) throws IOException {
           Request request = new Request.Builder()
                   .url(url)
                   .build();
           try (Response response = client.newCall(request).execute()) {
               return response.body().string();
           }
       }
       

       public static String postRequest(String url, String json) throws IOException {
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
           String text = OkHttpUtils.getRequest(url);
           System.out.println("url: " + url);
           System.out.println("response: " + text);

           OkHttpUtils.client = null;
       }
}
