package com.durocrete_client.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 15/6/17.
 */

public class Completemixmaterial implements Serializable{

    private List<Mixmaterialdesign> mixmaterialdesign = null;
    private List<Mixdesign> mixdesign=null;

    public List<Mixmaterialdesign> getMixmaterialdesign() {
        return mixmaterialdesign;
    }

    public void setMixmaterialdesign(List<Mixmaterialdesign> mixmaterialdesign) {
        this.mixmaterialdesign = mixmaterialdesign;
    }

    public List<Mixdesign> getMixdesign() {
        return mixdesign;
    }

    public void setMixdesign(List<Mixdesign> mixdesign) {
        this.mixdesign = mixdesign;
    }


}
