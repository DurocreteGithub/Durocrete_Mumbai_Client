package com.durocrete_client.model;

/**
 * Created by root on 17/6/17.
 */

public class Testarr {

    private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return testName;

    }
}
