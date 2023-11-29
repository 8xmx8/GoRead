package com.yjy.read.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionUtil {
    public static String qidianStr = "https://book.qidian.com/info/1021617576";
    public static String qidianStr1 = "https://read.qidian.com/chapter/O9zPuzOQBNt7DVpbqm07HA2/XSWAxiT0LDnM5j8_3RRvhw2/";

    public static void main(String[] args) {
        String result = doGet(qidianStr);
        System.out.println(result);
    }

    public static String doGet(String str) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = null;
        InputStream in = null;
        BufferedReader br = null;

        try {
            // 创建url
            URL url = new URL(str);
            // 打开连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置连接的请求方式
            conn.setRequestMethod("GET");
            // 连接超时时间  读取超时时间
            //  客户端和服务端先建立连接  连接成功后读取数据  数据读取完  响应成功
            //  连接时间  受客户端的带宽  客户端和服务端的距离  服务端自身的响应速度等因素影响
            //  读取时间  受服务端返回数据的大小  所影响
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);
            // 设置请求参数
            conn.setRequestProperty("Accept", "applicaion/json");
            // 发送请求
            conn.connect();
            // 判断响应码是否是200
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line);
//                    System.out.println(line);
                }
            } else {
                System.out.println("ResponseCode is not 200, is " + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (in != null) {
                    in.close();
                }
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
