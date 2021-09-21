package com.innovertech.puzzlehero.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innovertech.puzzlehero.model.Auth.AuthResponse;
import com.innovertech.puzzlehero.model.Auth.AuthSignIn;
import com.innovertech.puzzlehero.model.Auth.AuthSignUp;
import com.innovertech.puzzlehero.repository.AuthRepository;
import com.innovertech.puzzlehero.util.RestProgressCallback;

import org.jetbrains.annotations.NotNull;

public class AuthViewmodel extends AndroidViewModel {

    private AuthRepository authRepository;
    public LiveData<AuthResponse> authResponseSignUp;
    public LiveData<AuthResponse> authResponseSignIn;

    public AuthViewmodel(@NonNull @NotNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance(application);
    }

    public void userSignUp(AuthSignUp authSignUp, RestProgressCallback restProgressCallback) {
        authResponseSignUp = authRepository.userSignUp(authSignUp, restProgressCallback);
    }

    public void userSignIn(AuthSignIn authSignIn, RestProgressCallback restProgressCallback) {
        authResponseSignIn = authRepository.userSignIn(authSignIn, restProgressCallback);
    }
}
