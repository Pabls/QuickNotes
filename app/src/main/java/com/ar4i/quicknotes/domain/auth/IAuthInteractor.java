package com.ar4i.quicknotes.domain.auth;

import com.ar4i.quicknotes.data.models.UserVm;

import io.reactivex.Single;

public interface IAuthInteractor {

    Single<Boolean> createUser(String email, String password);

    Single<UserVm> getUser();

    Single<Boolean> signIn(String email, String password);

    boolean isEmailValid(String email);

    boolean isPasswordValid(String password);
}
