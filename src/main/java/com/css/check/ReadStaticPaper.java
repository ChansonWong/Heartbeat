package com.css.check;

import com.css.utils.SourceConfigureUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ReadStaticPaper {

    private static Logger logger = Logger.getLogger(ReadStaticPaper.class);

    public static void readHttpFile(String ipAndPort){
        SourceConfigureUtils utils = SourceConfigureUtils.getInstance();
        String paperMinSize = utils.getPaperMinSize();
        String staticPaperPath = utils.getStaticPaperPath();

        URLConnection conn = null;

        FileOutputStream out = null;
        InputStream is = null;

        try {
            conn = new URL("http://" + ipAndPort + staticPaperPath).openConnection();
            is = conn.getInputStream();


            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            float size = (float)(sb.toString().getBytes("utf-8").length / 1024);

            if (Float.parseFloat(paperMinSize) > size){
                logger.error("服务器" + ipAndPort + ", 获取到的试卷静态文件大小为" + size + "KB, 比约定的" + Float.parseFloat(paperMinSize) + "KB要小");
            }else{
                logger.info("服务器" + ipAndPort + ", 获取到的试卷静态文件大小为" + size + "KB, 而约定的大小为" + Float.parseFloat(paperMinSize) + "KB, 属于正常");
            }
        } catch (IOException e) {
            logger.error("异常信息：" +   e.getMessage());
            logger.error("服务器" + ipAndPort + "执行获取试卷静态文件请求异常");
            e.printStackTrace();
        }finally {
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
