package com.innovertech.puzzlehero.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResponse;
import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResult;
import com.innovertech.puzzlehero.model.PuzzleQuestion.SubmitAnswer;
import com.innovertech.puzzlehero.network.APIService;
import com.innovertech.puzzlehero.network.RetrofitInstance;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PuzzleRepository {
    private static PuzzleRepository puzzleRepoInstance;
    private SessionManager sessionManager;

    public PuzzleRepository(Context context) {
        sessionManager = SessionManager.getInstance(context);
    }

    public static PuzzleRepository getInstance(Context context) {
        if (puzzleRepoInstance == null) {
            puzzleRepoInstance = new PuzzleRepository(context);
        }
        return puzzleRepoInstance;
    }

    public MutableLiveData<QuestionResponse> getQuestion(String puzzleCategory, RestProgressCallback restProgressCallback) {
        MutableLiveData<QuestionResponse> questionResponseMutableLiveData = new MutableLiveData<>();
        APIService apiService = RetrofitInstance.getInstance().getApiService();
        Call<QuestionResponse> questionResponseCall = apiService.getQuestion(puzzleCategory, sessionManager.getSubId());
        questionResponseCall.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                Log.d(TAG, "onQuestionResponse: " + response.body());
                questionResponseMutableLiveData.setValue(response.body());
                restProgressCallback.isRestCallCompleted(true);
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                Log.d(TAG, "onQuestionFailure: " + t.getMessage());
                questionResponseMutableLiveData.postValue(null);
                restProgressCallback.isRestCallCompleted(true);
            }
        });
        return questionResponseMutableLiveData;
    }

    public MutableLiveData<QuestionResult> submitAnswer(SubmitAnswer submitAnswer, RestProgressCallback restProgressCallback) {
        MutableLiveData<QuestionResult> resultMutableLiveData = new MutableLiveData<>();
        APIService apiService = RetrofitInstance.getInstance().getApiService();
        Call<QuestionResult> resultCall = apiService.getResult(submitAnswer, sessionManager.getSubId());
        resultCall.enqueue(new Callback<QuestionResult>() {
            @Override
            public void onResponse(Call<QuestionResult> call, Response<QuestionResult> response) {
                Log.d(TAG, "onResultResponse: " + response.body());
                resultMutableLiveData.setValue(response.body());
                restProgressCallback.isRestCallCompleted(true);
            }

            @Override
            public void onFailure(Call<QuestionResult> call, Throwable t) {
                Log.d(TAG, "onResultFailure: " + t.getMessage());
                resultMutableLiveData.postValue(null);
                restProgressCallback.isRestCallCompleted(true);
            }
        });

        return resultMutableLiveData;
    }
}
