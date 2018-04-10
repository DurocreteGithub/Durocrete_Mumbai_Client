package com.durocrete_client.model;


public class  Mixdesign {

    private String slump;
    private String Natureofwork;
    private String specialrequirment;
    private String typeofdesign;
    private String grade;
    private String Serialno;
    private String Enquiry_id;

    public String getEnquiry_id() {
        return Enquiry_id;
    }

    public void setEnquiry_id(String enquiry_id) {
        Enquiry_id = enquiry_id;
    }

    public String getSerialno() {
        return Serialno;
    }

    public void setSerialno(String serialno) {
        Serialno = serialno;
    }


    public String getSlump() {
        return slump;
    }

    public void setSlump(String slump) {
        this.slump = slump;
    }

    public String getNatureofwork() {
        return Natureofwork;
    }

    public void setNatureofwork(String natureofwork) {
        Natureofwork = natureofwork;
    }

    public String getSpecialrequirment() {
        return specialrequirment;
    }

    public void setSpecialrequirment(String specialrequirment) {
        this.specialrequirment = specialrequirment;
    }

    public String getTypeofdesign() {
        return typeofdesign;
    }

    public void setTypeofdesign(String typeofdesign) {
        this.typeofdesign = typeofdesign;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}