package com.ar4i.quicknotes.data.repository.resource;

import android.content.Context;

public class ResourceRepository implements IResourceRepository {

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
