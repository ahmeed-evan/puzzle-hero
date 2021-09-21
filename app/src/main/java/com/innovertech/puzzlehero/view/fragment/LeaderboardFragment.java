package com.innovertech.puzzlehero.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.util.CustomLoadingDialog;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.UtilClass;
import com.innovertech.puzzlehero.view.adapter.LeaderboardAdapter;
import com.innovertech.puzzlehero.viewmodel.LeaderboardViewmodel;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaderboardFragment extends Fragment implements RestProgressCallback {

    @BindView(R.id.totalPointTV)
    TextView totalPointTV;
    @BindView(R.id.leaderboardRecycler)
    RecyclerView leaderboardRecycler;
    @BindView(R.id.leaderboardLayout)
    ConstraintLayout leaderboardLayout;

    private CustomLoadingDialog customLoadingDialog;
    private LeaderboardViewmodel leaderboardViewmodel;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view
                = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        ButterKnife.bind(this, view);
        initDialog();
        initViewmodel();
        getLeaders();
        return view;
    }

    private void getLeaders() {
        leaderboardViewmodel.getLeaders(this::isRestCallCompleted);
        customLoadingDialog.startLoadingDialog();
        initLeaderRecyclerView();
    }

    private void initLeaderRecyclerView() {
        leaderboardRecycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        leaderboardViewmodel.leaderboardResponseLiveData.observe(getViewLifecycleOwner(), leaderboardResponse -> {
            if (leaderboardResponse != null) {
                LeaderboardAdapter leaderboardAdapter = new LeaderboardAdapter(requireActivity(), leaderboardResponse.leadersList);
                leaderboardAdapter.notifyDataSetChanged();
                leaderboardRecycler.setAdapter(leaderboardAdapter);
                totalPointTV.setText(String.valueOf(leaderboardResponse.myPoints));
            } else {
                UtilClass.showSnackBar(leaderboardLayout, "Something went wrong! Please try again.");
            }
        });
    }

    private void initDialog() {
        customLoadingDialog = new CustomLoadingDialog(requireActivity());
    }

    private void initViewmodel() {
        leaderboardViewmodel = new ViewModelProvider(requireActivity()).get(LeaderboardViewmodel.class);
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
