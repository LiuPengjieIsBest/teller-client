package com.panda.teller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by root on 16-10-30.
 * http处理工具
 * sendHttpGETRequest用于发送GET请求，listener中有两个回调函数，分别用于对返回数据和异常进行处理
 */

public class HttpUtil {

    public static void sendHttpGETRequest(final String address, final HttpResponseListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    // 初始化链接
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    // connection.setDoInput(true);
                    // connection.setDoOutput(true);

                    // 读取流
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        response.append(line + "\n"); // 加\n是为了debug用
                    }
                    if(listener != null) {
                        listener.onResponse(response.toString());
                    }
                } catch(MalformedURLException e) {
                    if(listener != null) {
                        listener.onError(e);
                    }
                } catch(IOException e) {
                    if(listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if(connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}