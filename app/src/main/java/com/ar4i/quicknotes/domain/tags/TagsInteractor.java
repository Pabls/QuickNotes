package com.ar4i.quicknotes.domain.tags;

import com.ar4i.quicknotes.data.models.TagVm;
import com.ar4i.quicknotes.data.repositories.firebaserealtime.IFirebaseRealtimeRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class TagsInteractor implements ITagsInteractor {

    @Inject
    public TagsInteractor(IFirebaseRealtimeRepository iFirebaseRealtimeRepository) {
        this.iFirebaseRealtimeRepository = iFirebaseRealtimeRepository;
    }

    //==========================================start Fields========================================

    IFirebaseRealtimeRepository iFirebaseRealtimeRepository;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start implements ITagsInteracto=====================

    @Override
    public Completable sendTag(TagVm tagVm) {
        return iFirebaseRealtimeRepository.sendTag(tagVm);
    }

    @Override
    public Observable<TagVm> getTags() {
        return iFirebaseRealtimeRepository.getTags();
    }

    //-------------------------------------------end implements ITagsInteracto----------------------

}
