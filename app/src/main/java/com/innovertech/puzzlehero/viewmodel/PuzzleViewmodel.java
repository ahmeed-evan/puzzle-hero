package com.innovertech.puzzlehero.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResponse;
import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResult;
import com.innovertech.puzzlehero.model.PuzzleQuestion.SubmitAnswer;
import com.innovertech.puzzlehero.repository.PuzzleRepository;
import com.innovertech.puzzlehero.util.RestProgressCallback;

import org.jetbrains.annotations.NotNull;

public class PuzzleViewmodel extends AndroidViewModel {

    public LiveData<QuestionResponse> questionResponseLiveData;
    private PuzzleRepository puzzleRepository;
    public LiveData<QuestionResult> answerResultLiveData;

    public PuzzleViewmodel(@NonNull @NotNull Application application) {
        super(application);
        puzzleRepository = PuzzleRepository.getInstance(application);
    }

    public void getQuestion(String puzzleCategory, RestProgressCallback restProgressCallback) {
        questionResponseLiveData = puzzleRepository.getQuestion(puzzleCategory, restProgressCallback);
    }

    public void submitAnswer(SubmitAnswer submitAnswer, RestProgressCallback restProgressCallback) {
        answerResultLiveData = puzzleRepository.submitAnswer(submitAnswer, restProgressCallback);
    }
}
