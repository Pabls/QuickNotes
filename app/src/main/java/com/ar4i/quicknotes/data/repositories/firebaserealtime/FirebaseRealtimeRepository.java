package com.ar4i.quicknotes.data.repositories.firebaserealtime;

import com.ar4i.quicknotes.data.entities.Note;
import com.ar4i.quicknotes.data.models.NoteVm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class FirebaseRealtimeRepository implements IFirebaseRealtimeRepository {

    //==========================================start implements IFirebaseRealtimeRepository========

    @Override
    public Completable sendNote(NoteVm noteVm) {
        return Completable.create(emitter -> {
            DatabaseReference dbRef =  FirebaseDatabase.getInstance().getReference(noteVm.getUserId());
            dbRef.push().setValue(new Note(noteVm.getTimestamp(), noteVm.getTitle(), noteVm.getBody()));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<NoteVm>> getNotes(String userId) {
        return Observable.create(emitter -> {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ym9qD9rcgNXGGa97hdzUZ3EFM912");
            dbRef.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String path = dataSnapshot.getRef().toString();
                    List<NoteVm> noteVms = new ArrayList<>();
                    Iterator<DataSnapshot> notes = dataSnapshot.getChildren().iterator();
                    while (notes.hasNext()) {
                        Note note = (Note) notes.next().getValue();
                        noteVms.add(new NoteVm(note.timestamp, note.title, note.body, userId));
                    }
                    emitter.onNext(noteVms);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        });
    }

    //-------------------------------------------end implements IFirebaseRealtimeRepository---------
}
