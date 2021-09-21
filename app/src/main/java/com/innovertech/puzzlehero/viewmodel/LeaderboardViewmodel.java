package com.innovertech.puzzlehero.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innovertech.puzzlehero.model.Leaderboard.LeaderboardResponse;
import com.innovertech.puzzlehero.repository.LeaderboardRepository;
import com.innovertech.puzzlehero.util.RestProgressCallback;

import org.jetbrains.annotations.NotNull;

public class LeaderboardViewmodel extends AndroidViewModel {

    private LeaderboardRepository leaderboardRepository;
    public LiveData<LeaderboardResponse> leaderboardResponseLiveData;

    public LeaderboardViewmodel(@NonNull @NotNull Application application) {
        super(application);
        leaderboardRepository = LeaderboardRepository.getInstance(application);
    }

    public void getLeaders(RestProgressCallback restProgressCallback){
        leaderboardResponseLiveData=leaderboardRepository.getLeaders(restProgressCallback);
    }
}
