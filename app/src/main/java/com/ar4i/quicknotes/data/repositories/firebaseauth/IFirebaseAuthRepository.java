package com.ar4i.quicknotes.data.repositories.firebaseauth;

import com.ar4i.quicknotes.data.models.UserVm;

import io.reactivex.Single;

public interface IFirebaseAuthRepository {
    Single<UserVm> createUser(String email, String password);

    Single<UserVm> signIn(String email, String password);

    Single<UserVm> getUser();
}
