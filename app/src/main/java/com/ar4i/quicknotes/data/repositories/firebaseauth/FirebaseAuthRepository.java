package com.ar4i.quicknotes.data.repositories.firebaseauth;

import com.ar4i.quicknotes.data.models.UserVm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

public class FirebaseAuthRepository implements IFirebaseAuthRepository {

    @Override
    public Single<Boolean> createUser(String email, String password) {
        return Single.create(emitter ->
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                emitter.onError(task.getException());
                            }
                            emitter.onSuccess(task.isSuccessful());
                        }));
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
    public Single<UserVm> getUser() {
        return Single.create(emitter -> {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            UserVm userVm;
            if (firebaseUser != null) {
                userVm = new UserVm(firebaseUser.getEmail(), firebaseUser.getUid());
            } else {
                userVm = new UserVm();
            }
            emitter.onSuccess(userVm);
        });
    }
}
