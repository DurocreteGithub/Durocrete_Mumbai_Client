package com.durocrete_client.model;

/**
 * Created by root on 17/6/17.
 */

public class Enquiryiddetails {

    private String material_name;
    private int material_id;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    private String material_quantity;
    private String Contactname;
    private String Mobileno;
    private String EmailId;

    public String getContactname() {
        return Contactname;
    }

    public void setContactname(String contactname) {
        Contactname = contactname;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_quantity() {
        return material_quantity;
    }

    public void setMaterial_quantity(String material_quantity) {
        this.material_quantity = material_quantity;
    }


}
