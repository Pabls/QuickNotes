package com.ar4i.quicknotes.data.models;

import java.io.Serializable;

public class TagVm implements Serializable {

    //==========================================start Fields========================================

    private String name;
    private int color;
    private String userId;
    private boolean checked;

    //-------------------------------------------end Fields-----------------------------------------


    public TagVm(String name, int color, String userId) {
        this.name = name;
        this.color = color;
        this.userId = userId;
    }

    //==========================================start SET/GET=======================================

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public String getUserId() {
        return userId;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void checkTag() {
        checked = !checked;
    }

    public boolean isChecked() {
        return checked;
    }

    //-------------------------------------------end SET/GET----------------------------------------
}
