package com.innovertech.puzzlehero.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.innovertech.puzzlehero.model.Leaderboard.LeaderboardResponse;
import com.innovertech.puzzlehero.network.APIService;
import com.innovertech.puzzlehero.network.RetrofitInstance;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LeaderboardRepository {

    private static LeaderboardRepository leaderboardRepoInstance;
    private SessionManager sessionManager;

    public LeaderboardRepository(Context context) {
        sessionManager = SessionManager.getInstance(context);
    }

    public static LeaderboardRepository getInstance(Context context) {
        if (leaderboardRepoInstance == null) {
            leaderboardRepoInstance = new LeaderboardRepository(context);
        }
        return leaderboardRepoInstance;
    }

    public MutableLiveData<LeaderboardResponse> getLeaders(RestProgressCallback restProgressCallback) {
        MutableLiveData<LeaderboardResponse> responseMutableLiveData = new MutableLiveData<>();
        APIService apiService = RetrofitInstance.getInstance().getApiService();
        Call<LeaderboardResponse> responseCall = apiService.getLeaders(sessionManager.getSubId());
        responseCall.enqueue(new Callback<LeaderboardResponse>() {
            @Override
            public void onResponse(Call<LeaderboardResponse> call, Response<LeaderboardResponse> response) {
                Log.d(TAG, "onLeadersResponse: " + response.body());
                responseMutableLiveData.setValue(response.body());
                restProgressCallback.isRestCallCompleted(true);
            }

            @Override
            public void onFailure(Call<LeaderboardResponse> call, Throwable t) {
                Log.d(TAG, "onLeadersFailure: " + t.getMessage());
                responseMutableLiveData.postValue(null);
                restProgressCallback.isRestCallCompleted(true);
            }
        });
        return responseMutableLiveData;
    }
}
