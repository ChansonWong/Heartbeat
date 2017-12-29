package com.css.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HeartbeatMain {
    public static void main(String[] args){
        getRequest();
    }

    private static void getRequest(){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // 声明Get请求，并填上地址
            HttpGet httpGet = new HttpGet("http://localhost:8080/nwpp/sword?ctrl=XxCommonCtrl_connect");
            // 接受Get请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            // 状态码
            System.out.println("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            // print result
            System.out.println(response.toString());
            httpClient.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
