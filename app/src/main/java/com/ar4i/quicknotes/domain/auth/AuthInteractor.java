package com.ar4i.quicknotes.domain.auth;

import android.util.Patterns;

import com.ar4i.quicknotes.data.models.UserVm;
import com.ar4i.quicknotes.data.repositories.firebaseauth.IFirebaseAuthRepository;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.regex.Pattern;

import io.reactivex.Single;

public class AuthInteractor implements IAuthInteractor {

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final Pattern PATTERN = Patterns.EMAIL_ADDRESS;


    public AuthInteractor(IFirebaseAuthRepository iFirebaseAuthRepository) {
        this.iFirebaseAuthRepository = iFirebaseAuthRepository;
    }

    //==========================================start Fields========================================

    IFirebaseAuthRepository iFirebaseAuthRepository;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start implements IAuthInteractor==================

    @Override
    public boolean isEmailValid(String email) {
        return PATTERN.matcher(email).matches();
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    @Override
    public Single<Boolean> createUser(String email, String password) {
        return iFirebaseAuthRepository.createUser(email, password)
                .map(this::hasUser);
    }

    @Override
    public Single<UserVm> getUser() {
        return iFirebaseAuthRepository.getUser();
    }

    @Override
    public Single<Boolean> signIn(String email, String password) {
        return iFirebaseAuthRepository
                .signIn(email, password)
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof FirebaseAuthInvalidUserException)
                        return Single.just(new UserVm());
                    else
                        return Single.error(throwable);
                }).map(this::hasUser);
    }

    //-------------------------------------------end implements IAuthInteractor-------------------

    // region========================================Private methods================================

    private boolean hasUser(UserVm userVm) {
        return userVm != null && userVm.getUid() != null;
    }

    // endregion-------------------------------------Private methods--------------------------------
}
