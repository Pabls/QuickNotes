package com.ar4i.quicknotes.domain.signin;

import android.util.Patterns;

import com.ar4i.quicknotes.data.repository.firebaseauth.IFirebaseAuthRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

import io.reactivex.Single;

public class SignInInteractor implements ISignInInteractor {

    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final Pattern PATTERN = Patterns.EMAIL_ADDRESS;


    public SignInInteractor(IFirebaseAuthRepository iFirebaseAuthRepository) {
        this.iFirebaseAuthRepository = iFirebaseAuthRepository;
    }

    //==========================================start Fields========================================

    IFirebaseAuthRepository iFirebaseAuthRepository;

    //-------------------------------------------end Fields-----------------------------------------


    //==========================================start implements ISignInInteractor==================

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
        return iFirebaseAuthRepository.createUser(email, password);
    }

    @Override
    public Single<FirebaseUser> getUser() {
        return iFirebaseAuthRepository.getUser();
    }

    @Override
    public Single<Boolean> signIn(String email, String password) {
        return iFirebaseAuthRepository.createUser(email, password);
    }

    //-------------------------------------------end implements ISignInInteractor-------------------

}
