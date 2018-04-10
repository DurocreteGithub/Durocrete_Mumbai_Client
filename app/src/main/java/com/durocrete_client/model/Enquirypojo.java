package com.durocrete_client.model;

import java.util.ArrayList;

/**
 * Created by root on 19/5/17.
 */

public class Enquirypojo {

    String material;
    String materialid;
    String quantity;
    ArrayList<Testlist> testlistArrayList = null;
    int rate;
    String Siteid;
    String noofquantity;

    public String getNoofquantity() {
        return noofquantity;
    }

    public void setNoofquantity(String noofquantity) {
        this.noofquantity = noofquantity;
    }

    public String getSiteid() {
        return Siteid;
    }

    public void setSiteid(String siteid) {
        Siteid = siteid;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public ArrayList<Testlist> getTestlistArrayList() {
        return testlistArrayList;
    }

    public void setTestlistArrayList(ArrayList<Testlist> testlistArrayList) {
        this.testlistArrayList = testlistArrayList;
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

    @Override
    public String toString() {
        return  " Enquirypojo : material :" + material + " quantity :  "+ quantity +" ArrayListSize : " + testlistArrayList.toString();
    }
}
