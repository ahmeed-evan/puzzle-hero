package com.innovertech.puzzlehero.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.innovertech.puzzlehero.R;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationDrawer)
    NavigationView navigationDrawer;
    @BindView(R.id.clickToPlayButton)
    AppCompatButton clickToPlayButton;

    private boolean isDrawerOpen = false;
    private NavController navController;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initNavigationDrawer();
        return view;
    }

    private void initNavigationDrawer() {
        navController = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + "drawer_layout");
        if (isDrawerOpen) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }

    @OnClick(R.id.drawerButton)
    void drawerButtonClicked() {
        drawerLayout.openDrawer(GravityCompat.END);
        isDrawerOpen = true;
    }

    @OnClick(R.id.gKnowledgeTV)
    void gKnowledgeTVClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToPuzzleFragment("2"));
    }

    @OnClick(R.id.prizeTV)
    void prizeTVClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToPrizeFragment());
    }

    @OnClick(R.id.visualPuzzleTV)
    void visualPuzzleTVClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToPuzzleFragment("1"));
    }

    @OnClick(R.id.virtualPuzzle)
    void virtualPuzzleClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToPuzzleFragment("1"));
    }

    @OnClick(R.id.generalKnowledge)
    void generalKnowledgeClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToPuzzleFragment("2"));
    }

    @OnClick(R.id.myScoreTV)
    void myScoreTVClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToMyScoreFragment());
    }

    @OnClick(R.id.leaderboardTV)
    void leaderboardTVClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToLeaderboardFragment());
    }

    @OnClick(R.id.aboutUsTV)
    void aboutUsTVClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToAboutUsFragment());
    }

    @OnClick(R.id.clickToPlayButton)
    void clickToPlayButtonClicked() {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToPuzzleFragment("1"));
    }

}
