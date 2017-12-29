package com.css.entity;

import java.util.Date;

public class TRequestRecord {

    private String cId;
    private String cHostname;
    private String cIp;
    private String cPort;
    private Date cRequestTime;
    private String cResponseCode;
    private Date cResponseTime;
    private String cResponseMessage;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcHostname() {
        return cHostname;
    }

    public void setcHostname(String cHostname) {
        this.cHostname = cHostname;
    }

    public String getcIp() {
        return cIp;
    }

    public void setcIp(String cIp) {
        this.cIp = cIp;
    }

    public String getcPort() {
        return cPort;
    }

    public void setcPort(String cPort) {
        this.cPort = cPort;
    }

    public Date getcRequestTime() {
        return cRequestTime;
    }

    public void setcRequestTime(Date cRequestTime) {
        this.cRequestTime = cRequestTime;
    }

    public String getcResponseCode() {
        return cResponseCode;
    }

    public void setcResponseCode(String cResponseCode) {
        this.cResponseCode = cResponseCode;
    }

    public Date getcResponseTime() {
        return cResponseTime;
    }

    public void setcResponseTime(Date cResponseTime) {
        this.cResponseTime = cResponseTime;
    }

    public String getcResponseMessage() {
        return cResponseMessage;
    }

    public void setcResponseMessage(String cResponseMessage) {
        this.cResponseMessage = cResponseMessage;
    }

    @Override
    public String toString() {
        return "TRequestRecord{" +
                "cId='" + cId + '\'' +
                ", cHostname='" + cHostname + '\'' +
                ", cIp='" + cIp + '\'' +
                ", cPort='" + cPort + '\'' +
                ", cRequestTime=" + cRequestTime +
                ", cResponseCode='" + cResponseCode + '\'' +
                ", cResponseTime=" + cResponseTime +
                ", cResponseMessage='" + cResponseMessage + '\'' +
                '}';
    }
}
