package com.innovertech.puzzlehero.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.innovertech.puzzlehero.model.Auth.AuthResponse;
import com.innovertech.puzzlehero.model.Auth.AuthSignIn;
import com.innovertech.puzzlehero.model.Auth.AuthSignUp;
import com.innovertech.puzzlehero.network.APIService;
import com.innovertech.puzzlehero.network.RetrofitInstance;
import com.innovertech.puzzlehero.util.RestProgressCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class AuthRepository {

    private static AuthRepository authRepoInstance;

    public AuthRepository(Context context) {

    }

    public static AuthRepository getInstance(Context context) {
        if (authRepoInstance == null) {
            authRepoInstance = new AuthRepository(context);
        }
        return authRepoInstance;
    }

    public MutableLiveData<AuthResponse> userSignUp(AuthSignUp authSignUp, RestProgressCallback restProgressCallback) {
        MutableLiveData<AuthResponse> mutableLiveData = new MutableLiveData<>();
        APIService apiService = RetrofitInstance.getInstance().getApiService();
        Call<AuthResponse> responseCall = apiService.userSignUp(authSignUp);
        responseCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.d(TAG, "onAuthResponse: " + response.body());
                mutableLiveData.setValue(response.body());
                restProgressCallback.isRestCallCompleted(true);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.d(TAG, "onAuthResponseFailure: " + t.getMessage());
                mutableLiveData.postValue(null);
                restProgressCallback.isRestCallCompleted(true);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<AuthResponse> userSignIn(AuthSignIn authSignIn, RestProgressCallback restProgressCallback) {
        MutableLiveData<AuthResponse> mutableLiveData = new MutableLiveData<>();
        APIService apiService = RetrofitInstance.getInstance().getApiService();
        Call<AuthResponse> responseCall = apiService.userSignIn(authSignIn);
        responseCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                Log.d(TAG, "onAuthResponse: " + response.body());
                mutableLiveData.setValue(response.body());
                restProgressCallback.isRestCallCompleted(true);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.d(TAG, "onAuthResFailure: " + t.getMessage());
                mutableLiveData.postValue(null);
                restProgressCallback.isRestCallCompleted(true);
            }
        });
        return mutableLiveData;
    }
}
