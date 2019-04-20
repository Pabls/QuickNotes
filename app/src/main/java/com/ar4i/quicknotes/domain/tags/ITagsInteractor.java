package com.ar4i.quicknotes.domain.tags;

import com.ar4i.quicknotes.data.models.TagVm;

import io.reactivex.Completable;

public interface ITagsInteractor {
    Completable sendTag(TagVm tagVm);
}
