package com.yjy.read.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    public static String qidianStr = "https://book.qidian.com/info/1021617576";
    public static String urlStr = "https://read.qidian.com/chapter/O9zPuzOQBNt7DVpbqm07HA2/XSWAxiT0LDnM5j8_3RRvhw2/";

    public static void main(String[] args) {
        String result = doGet(qidianStr);
        System.out.println(result);
    }

    public static String doGet(String str) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        // 创建client对象  通过工具提供的默认配置
        httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(str);
        // 设置请求头
        httpGet.addHeader("Accept", "application/json");

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(15000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000)
                .build();

        // 将连接进行复用  连接池
        // ConnectionRequestTimeout  从连接池获取连接的超时时间
        // SocketTimeout 对应原有的ReadTimeout

        httpGet.setConfig(config);
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
