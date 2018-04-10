package com.durocrete_client.model;

/**
 * Created by root on 5/6/17.
 */

public class Siteinfo {



    public String getSiteaddress() {
        return Siteaddress;
    }

    public void setSiteaddress(String siteaddress) {
        Siteaddress = siteaddress;
    }

    private String siteId;
    private String siteName;
    private String Siteaddress;
    private String Site_mail;
    private String siteLatitude;
    private String siteLongitude;
    private String SITE_CL_Id;
    private String Contactpersonname;
    private String Contactno;
    private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactpersonname() {
        return Contactpersonname;
    }

    public void setContactpersonname(String contactpersonname) {
        Contactpersonname = contactpersonname;
    }

    public String getContactno() {
        return Contactno;
    }

    public void setContactno(String contactno) {
        Contactno = contactno;
    }

    public String getSITE_CL_Id() {
        return SITE_CL_Id;
    }

    public void setSITE_CL_Id(String SITE_CL_Id) {
        this.SITE_CL_Id = SITE_CL_Id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }



    public String getSite_mail() {
        return Site_mail;
    }

    public void setSite_mail(String site_mail) {
        Site_mail = site_mail;
    }

    public String getSiteLatitude() {
        return siteLatitude;
    }

    public void setSiteLatitude(String siteLatitude) {
        this.siteLatitude = siteLatitude;
    }

    public String getSiteLongitude() {
        return siteLongitude;
    }

    public void setSiteLongitude(String siteLongitude) {
        this.siteLongitude = siteLongitude;
    }



}
