package com.innovertech.puzzlehero.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.util.CustomLoadingDialog;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.viewmodel.ScoreHistoryViewmodel;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyScoreFragment extends Fragment implements RestProgressCallback {

    @BindView(R.id.totalPointTV)
    TextView totalPointTV;
    @BindView(R.id.totalQuestionAnsweredTV)
    TextView totalQuestionAnsweredTV;
    @BindView(R.id.totalCorrectAnsweredTV)
    TextView totalCorrectAnsweredTV;
    @BindView(R.id.totalWrongAnsweredTV)
    TextView totalWrongAnsweredTV;
    @BindView(R.id.thisMonthsTotaTV)
    TextView thisMonthsTotaTV;
    @BindView(R.id.thisMonthsTotalPointTV)
    TextView thisMonthsTotalPointTV;

    private CustomLoadingDialog customLoadingDialog;
    private ScoreHistoryViewmodel scoreHistoryViewmodel;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view
                = inflater.inflate(R.layout.fragment_my_score, container, false);
        ButterKnife.bind(this, view);
        initDialog();
        initViewmodel();
        getScoreHistory();
        return view;
    }

    private void getScoreHistory() {
        scoreHistoryViewmodel.getScoreHistory(this::isRestCallCompleted);
        customLoadingDialog.startLoadingDialog();
        updateScoreScreen();
    }

    private void updateScoreScreen() {
        scoreHistoryViewmodel.scoreResponseLiveData.observe(getViewLifecycleOwner(), scoreResponse -> {
            if (scoreResponse != null) {
                if (scoreResponse.msisdnFound.equals("1")) {
                    totalPointTV.setText(String.valueOf(scoreResponse.result.totalPoint) + " points");
                    totalQuestionAnsweredTV.setText(String.valueOf(scoreResponse.result.total));
                    totalCorrectAnsweredTV.setText(String.valueOf(scoreResponse.result.correct));
                    totalWrongAnsweredTV.setText(String.valueOf(scoreResponse.result.wrong));
                    thisMonthsTotaTV.setText(String.valueOf(scoreResponse.result.thisMonthTotal));
                    thisMonthsTotalPointTV.setText(String.valueOf(scoreResponse.result.thisMonthTotalPoint) + " points");
                }
            }
        });
    }

    private void initViewmodel() {
        scoreHistoryViewmodel = new ViewModelProvider(requireActivity()).get(ScoreHistoryViewmodel.class);
    }

    private void initDialog() {
        customLoadingDialog = new CustomLoadingDialog(requireActivity());
    }

    @Override
    public void isRestCallCompleted(boolean isCompleted) {
        if (isCompleted) customLoadingDialog.stopLoadingDialog();
    }

    @OnClick(R.id.backArrow)
    void backArrowClicked() {
        NavHostFragment.findNavController(this)
                .popBackStack();
    }
}
