package com.durocrete_client.adapter;

import android.os.Environment;

/**
 * Created by root on 13/11/17.
 */

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}