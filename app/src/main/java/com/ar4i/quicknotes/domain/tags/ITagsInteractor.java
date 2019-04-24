package com.ar4i.quicknotes.domain.tags;

import com.ar4i.quicknotes.data.models.TagVm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ITagsInteractor {

    Completable sendTag(TagVm tagVm);

    Observable<List<TagVm>> getTags();
}
