package com.durocrete_client.activity;

import java.util.ArrayList;

/**
 * Created by root on 30/10/17.
 */

public class CubeTestrequestform {

    String material;
    String material_id;
    String testname;
    String Grade;
    String casting_dt;
    String Nature_of_work;
    String Supplier;
    String No_of_sets;
    ArrayList tests;
    String Enquiry_id;
    ArrayList sets;

    public ArrayList getSets() {
        return sets;
    }

    public void setSets(ArrayList sets) {
        this.sets = sets;
    }

    public String getEnquiry_id() {
        return Enquiry_id;
    }

    public void setEnquiry_id(String enquiry_id) {
        Enquiry_id = enquiry_id;
    }

    public ArrayList getTests() {
        return tests;
    }

    public void setTests(ArrayList tests) {
        this.tests = tests;
    }



    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getCasting_dt() {
        return casting_dt;
    }

    public void setCasting_dt(String casting_dt) {
        this.casting_dt = casting_dt;
    }

    public String getNature_of_work() {
        return Nature_of_work;
    }

    public void setNature_of_work(String nature_of_work) {
        Nature_of_work = nature_of_work;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getNo_of_sets() {
        return No_of_sets;
    }

    public void setNo_of_sets(String no_of_sets) {
        No_of_sets = no_of_sets;
    }


}





