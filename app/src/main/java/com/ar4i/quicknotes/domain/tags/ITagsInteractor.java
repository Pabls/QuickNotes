package com.ar4i.quicknotes.domain.tags;

import com.ar4i.quicknotes.data.models.TagVm;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ITagsInteractor {

    Completable sendTag(TagVm tagVm);

    Observable<TagVm> getTags();
}
