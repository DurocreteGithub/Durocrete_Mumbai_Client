package com.durocrete_client.activity;

import java.util.List;

/**
 * Created by root on 30/10/17.
 */

public class Testrequestform {

    private List<CubeTestrequestform> cubeTestrequestformList = null;
    private List<Steelrequestform> steeltestrequestformlist=null;
    private List<OtherTestrequestform> otherTestrequestformList=null;

    public List<Mixdesigntestrequestform> getMixdesigntestrequestformList() {
        return mixdesigntestrequestformList;
    }

    public void setMixdesigntestrequestformList(List<Mixdesigntestrequestform> mixdesigntestrequestformList) {
        this.mixdesigntestrequestformList = mixdesigntestrequestformList;
    }

    private List<Mixdesigntestrequestform> mixdesigntestrequestformList=null;

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    private String material_id;


    public List<CubeTestrequestform> getCubeTestrequestformList() {
        return cubeTestrequestformList;
    }

    public void setCubeTestrequestformList(List<CubeTestrequestform> cubeTestrequestformList) {
        this.cubeTestrequestformList = cubeTestrequestformList;
    }

    public List<Steelrequestform> getSteeltestrequestformlist() {
        return steeltestrequestformlist;
    }

    public void setSteeltestrequestformlist(List<Steelrequestform> steeltestrequestformlist) {
        this.steeltestrequestformlist = steeltestrequestformlist;
    }

    public List<OtherTestrequestform> getOtherTestrequestformList() {
        return otherTestrequestformList;
    }

    public void setOtherTestrequestformList(List<OtherTestrequestform> otherTestrequestformList) {
        this.otherTestrequestformList = otherTestrequestformList;
    }
}





