package com.ar4i.quicknotes.domain.resource;

import com.ar4i.quicknotes.data.repository.resource.IResourceRepository;

public class ResourceInteractor implements IResourceInteractor {

    public ResourceInteractor(IResourceRepository iResourceRepository) {
        this.iResourceRepository = iResourceRepository;
    }

    //==========================================start Fields========================================

    private IResourceRepository iResourceRepository;

    //-------------------------------------------end Fields-----------------------------------------

    //==========================================start implements IResourceInteractor================

    @Override
    public String getStringById(int id) {
        return iResourceRepository.getStringById(id);
    }

    //-------------------------------------------end implements IResourceInteractor-----------------

}
