package com.ar4i.quicknotes.data.entities;

import com.google.firebase.database.Exclude;

public class Tag {

    //==========================================start Fields========================================

    public String name;
    public int color;

    //-------------------------------------------end Fields-----------------------------------------

    public Tag() {
    }


    public Tag(String name, int color) {
        this.name = name;
        this.color = color;
    }

    //==========================================start SET/GET=======================================

    @Exclude
    public String getName() { return name; }

    @Exclude
    public void setName(String name) { this.name = name; }

    @Exclude
    public int getColor() { return color; }

    @Exclude
    public void setColor(int color) { this.color = color; }

    //-------------------------------------------end SET/GET----------------------------------------
}
