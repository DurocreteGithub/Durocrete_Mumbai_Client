package com.durocrete_client.model;

import java.util.ArrayList;

/**
 * Created by root on 13/10/17.
 */

public class Enquiryform {

    String material;
    String materialid;
    ArrayList tests;
    String contact_name;
    String contact_number;
    String email_id;
    String Client_id;
    String Site_id;

    public Boolean getEnquiryflag() {
        return enquiryflag;
    }

    public void setEnquiryflag(Boolean enquiryflag) {
        this.enquiryflag = enquiryflag;
    }

    Boolean enquiryflag;

    public String getClient_id() {
        return Client_id;
    }

    public void setClient_id(String client_id) {
        Client_id = client_id;
    }

    public String getSite_id() {
        return Site_id;
    }

    public void setSite_id(String site_id) {
        Site_id = site_id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public ArrayList getTests() {
        return tests;
    }

    public void setTests(ArrayList tests) {
        this.tests = tests;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }


}
