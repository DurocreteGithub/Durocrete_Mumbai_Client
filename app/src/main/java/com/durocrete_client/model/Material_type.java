package com.durocrete_client.model;

/**
 * Created by root on 29/8/17.
 */

public class Material_type {

  private String materialId;
    private String Material_List;
    private String Material_Type;
    private String Material_SrNo;
    private String Enquiry_id;

    public String getEnquiry_id() {
        return Enquiry_id;
    }

    public void setEnquiry_id(String enquiry_id) {
        Enquiry_id = enquiry_id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterial_List() {
        return Material_List;
    }

    public void setMaterial_List(String material_List) {
        Material_List = material_List;
    }

    public String getMaterial_Type() {
        return Material_Type;
    }

    public void setMaterial_Type(String material_Type) {
        Material_Type = material_Type;
    }

    public String getMaterial_SrNo() {
        return Material_SrNo;
    }

    public void setMaterial_SrNo(String material_SrNo) {
        Material_SrNo = material_SrNo;
    }

    @Override
    public String toString() {
        return Material_List ;

    }
}
