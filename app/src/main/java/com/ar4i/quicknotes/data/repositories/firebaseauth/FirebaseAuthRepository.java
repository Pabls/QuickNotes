package com.ar4i.quicknotes.data.repositories.firebaseauth;

import com.ar4i.quicknotes.data.models.UserVm;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

public class FirebaseAuthRepository implements IFirebaseAuthRepository {

    //==========================================start implements IFirebaseAuthRepository============

    @Override
    public Single<UserVm> createUser(String email, String password) {
        return Single.create(emitter ->
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                emitter.onError(task.getException());
                            }
                            emitter.onSuccess(getUserVm());
                        }));
    }

    @Override
    public Single<UserVm> signIn(String email, String password) {
        return Single.create(emitter ->
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onSuccess(getUserVm());
                            }
                        }));
    }

    @Override
    public Single<UserVm> getUser() {
        return Single.create(emitter ->
                emitter.onSuccess(getUserVm()));
    }

    //-------------------------------------------end implements IFirebaseAuthRepository-------------


    // region========================================Private methods================================

    private UserVm getUserVm() {
        return convertFirebaseUserToUserVm(getCurrentUser());
    }

    private UserVm convertFirebaseUserToUserVm(FirebaseUser firebaseUser) {
        UserVm userVm;
        if (firebaseUser != null) {
            userVm = new UserVm(firebaseUser.getEmail(), firebaseUser.getUid());
        } else {
            userVm = new UserVm();
        }
        return userVm;
    }

    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    // endregion-------------------------------------Private methods--------------------------------

}
