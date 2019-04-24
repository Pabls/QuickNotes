package com.ar4i.quicknotes.data.models;

import java.io.Serializable;

public class TagVm implements Serializable {

    //==========================================start Fields========================================

    private String name;
    private int color;
    private boolean checked;

    //-------------------------------------------end Fields-----------------------------------------


    public TagVm(String name, int color) {
        this.name = name;
        this.color = color;
    }

    //==========================================start SET/GET=======================================

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
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
