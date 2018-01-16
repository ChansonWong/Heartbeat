package com.css.job;

import com.css.check.ReadStaticPaper;
import com.css.check.TryLogin;
import com.css.utils.SourceConfigureUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class ValidateJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SourceConfigureUtils utils = SourceConfigureUtils.getInstance();

        List<String> ipAndPortList = utils.getIpAndPortList();

        for (String ipAndPort : ipAndPortList){
            // 校验登录
            TryLogin.getRequest(ipAndPort);
            // 校验静态文件
            ReadStaticPaper.readHttpFile(ipAndPort);
        }

    }
}
