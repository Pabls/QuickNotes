package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ar4i.quicknotes.data.entities.Tag;
import com.ar4i.quicknotes.data.models.TagVm;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class TagsEventListener implements ChildEventListener {

    // region========================================Fields=========================================

    private BehaviorSubject<TagVm> addedTagSub = BehaviorSubject.create();

    // endregion-------------------------------------Fields-----------------------------------------


    // region========================================implements ChildEventListener==================

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Tag addedTag = getTag(dataSnapshot);
        TagVm tagVm = convert(addedTag);
        setAddedTagVm(tagVm);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }

    // endregion-------------------------------------implements ChildEventListener------------------


    // region========================================Public methods=================================

    public Observable<TagVm> receivedTag() {
        return addedTagSub.distinctUntilChanged();
    }

    // endregion-------------------------------------Public methods---------------------------------


    // region========================================Private methods================================

    private Tag getTag(DataSnapshot dataSnapshot) {
        Tag tag;
        try {
            tag = dataSnapshot.getValue(Tag.class);
        } catch (Exception e) {
            tag = null;
        }
        return tag;
    }

    private TagVm convert(Tag addedTag) {
        TagVm tagVm = null;
        if (addedTag != null) {
            tagVm = new TagVm(addedTag.getName(), addedTag.getColor());
        }
        return tagVm;
    }

    private void setAddedTagVm(TagVm tagVm) {
        if (tagVm != null)
            addedTagSub.onNext(tagVm);
    }

    // endregion-------------------------------------Private methods--------------------------------
}
