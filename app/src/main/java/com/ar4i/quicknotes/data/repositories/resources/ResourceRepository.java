package com.ar4i.quicknotes.data.repositories.resources;

import android.content.Context;

import javax.inject.Inject;

public class ResourceRepository implements IResourceRepository {

    @Inject
    public ResourceRepository(Context context) {
        this.context = context;
    }

    //==========================================start Fields========================================

    private Context context;

    //-------------------------------------------end Fields-----------------------------------------

    //==========================================start implements IResourceRepository================

    @Override
    public String getStringById(int id) {
        return context.getResources().getString(id);
    }

    //-------------------------------------------end implements IResourceRepository------------------

}
