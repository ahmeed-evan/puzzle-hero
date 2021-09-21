package com.innovertech.puzzlehero.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innovertech.puzzlehero.model.ScoreHistory.ScoreResponse;
import com.innovertech.puzzlehero.repository.ScoreHistoryRepository;
import com.innovertech.puzzlehero.util.RestProgressCallback;

import org.jetbrains.annotations.NotNull;

public class ScoreHistoryViewmodel extends AndroidViewModel {
    public LiveData<ScoreResponse> scoreResponseLiveData;
    private ScoreHistoryRepository scoreHistoryRepository;

    public ScoreHistoryViewmodel(@NonNull @NotNull Application application) {
        super(application);
        scoreHistoryRepository = ScoreHistoryRepository.getInstance(application);
    }

    public void getScoreHistory(RestProgressCallback restProgressCallback){
        scoreResponseLiveData=scoreHistoryRepository.getScoreHistory(restProgressCallback);
    }
}
