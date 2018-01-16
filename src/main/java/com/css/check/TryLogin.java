package com.css.check;

import com.css.utils.SourceConfigureUtils;
import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TryLogin {
    private static Logger logger = Logger.getLogger(TryLogin.class);

    /*public static void main(String[] args){
        getRequest("172.16.11.40:7008");
    }*/

    /**
     * 1、登录（固定账号）
     * 2、取试卷 试卷大小（固定试卷）
     */

    public static void getRequest(String ipAndPort){
        SourceConfigureUtils utils = SourceConfigureUtils.getInstance();
        String loginPath = utils.getLoginPath();
        String loginKey = utils.getLoginKey();
        String loginValue = utils.getLoginValue();
        String loginCtrl = utils.getLoginCtrl();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        BufferedReader reader = null;

        try {
            HttpPost httpPost = new HttpPost("http://" + ipAndPort + loginPath);

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            Map<String, Object> dataReqest = new HashMap<String, Object>();
            dataReqest.put("name", loginKey);
            dataReqest.put("value", loginValue);
            dataReqest.put("sword", "attr");
            dataList.add(dataReqest);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("ctrl", loginCtrl);
            paramMap.put("data", dataList);

            Gson gson = new Gson();
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("postData",gson.toJson(paramMap)));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
            httpPost.setEntity(entity);

            // 接受POST请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            int code = httpResponse.getStatusLine().getStatusCode();

            if (code == 200){
                logger.info("服务器" + ipAndPort + ", 响应代码：" + httpResponse.getStatusLine().getStatusCode());

                reader = new BufferedReader(new InputStreamReader(
                        httpResponse.getEntity().getContent()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }

                String responseMsg = response.toString();
                if (responseMsg.contains("系统中身份证不存在")){
                    logger.error("服务器" + ipAndPort + ", 错误信息：账号" + loginValue + "在系统中身份证不存在");
                } else {
                    logger.info("服务器" + ipAndPort + ", 登录验证通过");
                    logger.info("响应报文信息：" + responseMsg);
                }

            }else{
                logger.error("服务器" + ipAndPort + "检验登录请求异常, 响应代码：" + httpResponse.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
