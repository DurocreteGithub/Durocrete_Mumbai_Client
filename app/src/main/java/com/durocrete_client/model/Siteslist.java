package com.durocrete_client.model;

import java.io.Serializable;

/**
 * Created by root on 20/5/17.
 */

public class Siteslist implements Serializable {

    String siteName;
    int siteId;


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return siteName;
    }
}
