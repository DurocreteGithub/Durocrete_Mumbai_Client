package com.durocrete_client.model;

/**
 * Created by root on 20/6/17.
 */

public class Siteengineerinfo {

    private String UserName;
    private String mobile;
    private String clientID;
    private String SiteID;
    private String siteName;
    private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
//    @Override
//    public String toString() {
//        return "Siteengineerinfo{" +
//                "UserName='" + UserName + '\'' +
//                ", mobile='" + mobile + '\'' +
//                ", clientID='" + clientID + '\'' +
//                ", SiteID='" + SiteID + '\'' +
//                '}';
//    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getSiteID() {
        return SiteID;
    }

    public void setSiteID(String siteID) {
        SiteID = siteID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
