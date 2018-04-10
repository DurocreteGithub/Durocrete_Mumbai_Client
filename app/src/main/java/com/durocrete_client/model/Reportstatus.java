package com.durocrete_client.model;

/**
 * Created by root on 5/6/17.
 */

public class Reportstatus {

    String Clientname;
    String Refernceno;
    String dateoftesting;
    String status;
    String download;

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getRefernceno() {
        return Refernceno;
    }

    public void setRefernceno(String refernceno) {
        Refernceno = refernceno;
    }

    public String getDateoftesting() {
        return dateoftesting;
    }

    public void setDateoftesting(String dateoftesting) {
        this.dateoftesting = dateoftesting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientname() {
        return Clientname;
    }

    public void setClientname(String clientname) {
        Clientname = clientname;
    }
}
