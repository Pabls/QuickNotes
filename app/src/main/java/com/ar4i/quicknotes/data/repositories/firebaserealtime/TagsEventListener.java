package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import androidx.annotation.NonNull;

import com.ar4i.quicknotes.data.entities.Tag;
import com.ar4i.quicknotes.data.models.TagVm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class TagsEventListener implements ValueEventListener {

    // region========================================Fields=========================================

    private BehaviorSubject<List<TagVm>> addedTagSub = BehaviorSubject.create();

    // endregion-------------------------------------Fields-----------------------------------------


    // region========================================implements ChildEventListener==================

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<TagVm> tagVms = new ArrayList<>();
        Iterable<DataSnapshot> iterableDataSnapshot = dataSnapshot.getChildren();
        try {
            Iterator<DataSnapshot> iterator = iterableDataSnapshot.iterator();
            while (iterator.hasNext()) {
                Tag res = iterator.next().getValue(Tag.class);
                if (res != null)
                    tagVms.add(new TagVm(res.getName(), res.getColor()));
            }
        } catch (Exception e) {
        }
        setAddedTagVms(tagVms);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }

    // endregion-------------------------------------implements ChildEventListener------------------


    // region========================================Public methods=================================

    public Observable<List<TagVm>> receivedTag() {
        return addedTagSub.distinctUntilChanged();
    }

    // endregion-------------------------------------Public methods---------------------------------


    // region========================================Private methods================================

    private void setAddedTagVms(List<TagVm> tagVms) {
        addedTagSub.onNext(tagVms);
    }

    // endregion-------------------------------------Private methods--------------------------------
}
