package com.durocrete_client.model;

import java.io.Serializable;

/**
 * Created by root on 20/5/17.
 */

public class Materiallist implements Serializable {

    String materialName;
  String materialId;
    String Quantity;
    boolean ischecked;

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public boolean getIsChecked() {
        return this.ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Override
    public String toString() {
        return materialName;
    }

    public boolean ischecked(boolean b) {
        return ischecked;
    }
}
