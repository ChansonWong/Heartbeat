package com.css.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SourceConfigureUtils {

    private static SourceConfigureUtils instance = null;

    private static List<String> ipAndPortList = new ArrayList<String>();
    private static String loginPath = "";
    private static String staticPaperPath = "";
    private static String paperMinSize = "";
    private static String loginKey = "";
    private static String loginValue = "";
    private static String loginCtrl = "";

    private SourceConfigureUtils(){

    }

    // 获取单例
    public static SourceConfigureUtils getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new SourceConfigureUtils();
            init();
        }
    }

    private static void init(){
        Properties prop = new Properties();
        InputStream in = null;

        try {
            in = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("configure.properties");
            prop.load(in);

            loginPath = prop.getProperty("LoginPath");
            staticPaperPath = prop.getProperty("StaticPaperPath");
            paperMinSize = prop.getProperty("PaperMinSize");
            loginKey = prop.getProperty("LoginKey");
            loginValue = prop.getProperty("LoginValue");
            loginCtrl = prop.getProperty("LoginCtrl");

            String ipAndPortStr = prop.getProperty("IpAndPort");
            String[] ipAndPortArray = ipAndPortStr.split(",");

            for (String ipAndPort : ipAndPortArray){
                ipAndPortList.add(ipAndPort.trim());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public List<String> getIpAndPortList(){
        return ipAndPortList;
    }

    public String getLoginPath(){
        return loginPath;
    }

    public String getStaticPaperPath(){
        return staticPaperPath;
    }

    public String getPaperMinSize(){
        return paperMinSize;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public String getLoginValue() {
        return loginValue;
    }

    public String getLoginCtrl() {
        return loginCtrl;
    }
}
