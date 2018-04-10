package com.durocrete_client.model;


import java.util.ArrayList;

public class Mixmaterialdesign {

    private String material;
    private String quantity;
    private String  information;
    private String materialserialno;
    private ArrayList<String> selectcheckboxlist = null;
    private String material_id;
    private String enquiry_id;

    public String getEnquiry_id() {
        return enquiry_id;
    }

    public void setEnquiry_id(String enquiry_id) {
        this.enquiry_id = enquiry_id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getMaterialserialno() {
        return materialserialno;
    }

    public void setMaterialserialno(String materialserialno) {
        this.materialserialno = materialserialno;
    }

    public ArrayList<String> getSelectcheckboxlist() {
        return selectcheckboxlist;
    }

    public void setSelectcheckboxlist(ArrayList<String> selectcheckboxlist) {
        this.selectcheckboxlist = selectcheckboxlist;
    }
}

