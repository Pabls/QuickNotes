package com.ar4i.quicknotes.data.models;

public class TagVm {

    //==========================================start Fields========================================

    private String name;
    private int color;
    private String userId;

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

    //-------------------------------------------end SET/GET----------------------------------------
}
