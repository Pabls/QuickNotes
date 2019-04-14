package com.ar4i.quicknotes.data.repository.firebaseauth;

import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

public interface IFirebaseAuthRepository {
    public Single<Boolean> createUser(String email, String password);

    public Single<Boolean> signIn(String email, String password);

    public Single<FirebaseUser> getUser();
}
