package com.durocrete_client.model;

/**
 * Created by root on 16/6/17.
 */

public class Enquiryid {


    private String result;
    private String enqId;

    public String getEnqId() {
        return enqId;
    }

    public void setEnqId(String enqId) {
        this.enqId = enqId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ""+enqId;
    }
}
