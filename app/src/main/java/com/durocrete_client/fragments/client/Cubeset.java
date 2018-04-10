package com.durocrete_client.fragments.client;

/**
 * Created by root on 30/10/17.
 */

public class Cubeset {

    String schedule;
    String idmark1;
    String idmark2;
    String idmark3;
    String schedule_other_no;
    boolean isfilled;

    public boolean getISsfilled() {
        return isfilled;
    }

    public void setIsfilled(boolean isfilled) {
        this.isfilled = isfilled;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    String id_no;



    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getIdmark1() {
        return idmark1;
    }

    public void setIdmark1(String idmark1) {
        this.idmark1 = idmark1;
    }

    public String getIdmark2() {
        return idmark2;
    }

    public void setIdmark2(String idmark2) {
        this.idmark2 = idmark2;
    }

    public String getIdmark3() {
        return idmark3;
    }

    public void setIdmark3(String idmark3) {
        this.idmark3 = idmark3;
    }

    public String getSchedule_other_no() {
        return schedule_other_no;
    }

    public void setSchedule_other_no(String schedule_other_no) {
        this.schedule_other_no = schedule_other_no;
    }

    @Override
    public String toString() {
        return "Cubeset{" +
                "schedule='" + schedule + '\'' +
                ", idmark1='" + idmark1 + '\'' +
                '}';
    }
}
