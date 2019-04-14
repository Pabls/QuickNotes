package com.ar4i.quicknotes.data.repository.firebaseauth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

public class FirebaseAuthRepository implements IFirebaseAuthRepository {

    @Override
    public Single<Boolean> createUser(String email, String password) {
        return Single.create(emitter ->
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> emitter.onSuccess(task.isSuccessful())));
    }

    @Override
    public Single<Boolean> signIn(String email, String password) {
        return Single.create(emitter ->
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                emitter.onError(task.getException());
                            }
                            emitter.onSuccess(task.isSuccessful());
                        }));
    }

    @Override
    public Single<FirebaseUser> getUser() {
        return Single.create(emitter -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            emitter.onSuccess(user);
        });
    }
}
