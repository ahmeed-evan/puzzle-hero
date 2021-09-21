package com.innovertech.puzzlehero.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.util.SessionManager;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashFragment extends Fragment {
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        ButterKnife.bind(this, view);
        setFullScreen();
//        gotoNextScreen();
        return view;
    }


    private void gotoNextScreen() {
        new Handler().postDelayed(() -> NavHostFragment.findNavController(SplashFragment.this)
                .navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment()), 3000);
    }

    private void setFullScreen() {
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @OnClick(R.id.playButtonIV)
    void playButtonIVClicked() {
        if (!(SessionManager.getInstance(requireActivity()).getSubId().isEmpty())) {
            NavHostFragment.findNavController(this)
                    .navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment());
        } else {
            NavHostFragment.findNavController(SplashFragment.this)
                    .navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment());
        }
    }
}
