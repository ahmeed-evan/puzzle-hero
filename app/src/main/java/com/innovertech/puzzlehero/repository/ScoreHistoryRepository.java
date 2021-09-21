package com.innovertech.puzzlehero.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.innovertech.puzzlehero.model.ScoreHistory.ScoreResponse;
import com.innovertech.puzzlehero.network.APIService;
import com.innovertech.puzzlehero.network.RetrofitInstance;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ScoreHistoryRepository {

    private static ScoreHistoryRepository scoreHistoryRepoInstance;
    private SessionManager sessionManager;

    public ScoreHistoryRepository(Context context) {
        sessionManager = SessionManager.getInstance(context);
    }

    public static ScoreHistoryRepository getInstance(Context context) {
        if (scoreHistoryRepoInstance == null) {
            scoreHistoryRepoInstance = new ScoreHistoryRepository(context);
        }
        return scoreHistoryRepoInstance;
    }

    public MutableLiveData<ScoreResponse> getScoreHistory(RestProgressCallback restProgressCallback) {
        MutableLiveData<ScoreResponse> responseMutableLiveData = new MutableLiveData<>();
        APIService apiService = RetrofitInstance.getInstance().getApiService();
        Call<ScoreResponse> responseCall = apiService.getScoreHistory(sessionManager.getSubId());
        responseCall.enqueue(new Callback<ScoreResponse>() {
            @Override
            public void onResponse(Call<ScoreResponse> call, Response<ScoreResponse> response) {
                Log.d(TAG, "onScoreHistoryResponse: " + response.body());
                responseMutableLiveData.setValue(response.body());
                restProgressCallback.isRestCallCompleted(true);
            }

            @Override
            public void onFailure(Call<ScoreResponse> call, Throwable t) {
                Log.d(TAG, "onScoreHistoryFailure: " + t.getMessage());
                responseMutableLiveData.postValue(null);
                restProgressCallback.isRestCallCompleted(true);
            }
        });


        return responseMutableLiveData;
    }
}
