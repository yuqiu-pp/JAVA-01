package com.nio1.client;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HttpClientUtils {

    public static CloseableHttpClient client = HttpClients.createDefault();


    public static String getRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = client.execute(httpGet);

        try{
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
            // EntityUtils.consume(entity);
        } finally {
            response.close();
        }
    }


    public static String postRequest(String url) throws IOException {
        HttpPost httpPost = new HttpPost("http://targethost/login");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "vip"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = client.execute(httpPost);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
            // do something useful with the response body
            // and ensure it is fully consumed
            // EntityUtils.consume(entity2);
        } finally {
            response.close();
        }
    }


    public static void main(String[] args) throws IOException {
        // String url = "https://square.github.io/okhttp/";
        String url = "http://localhost:8803";
        String text = HttpClientUtils.getRequest(url);
        System.out.println("url: " + url);
        System.out.println("response: " + text);

    }
}
