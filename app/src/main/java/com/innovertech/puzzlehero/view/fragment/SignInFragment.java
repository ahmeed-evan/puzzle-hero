package com.innovertech.puzzlehero.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.model.Auth.AuthSignIn;
import com.innovertech.puzzlehero.util.CustomLoadingDialog;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.SessionManager;
import com.innovertech.puzzlehero.util.UtilClass;
import com.innovertech.puzzlehero.viewmodel.AuthViewmodel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInFragment extends Fragment implements RestProgressCallback {

    @BindView(R.id.phnNumberET)
    EditText phnNumberET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.signInLayout)
    ConstraintLayout signInLayout;

    private AuthViewmodel authViewmodel;
    private CustomLoadingDialog customLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        initViewmodel();
        initDialog();
        return view;
    }

    private void initDialog() {
        customLoadingDialog = new CustomLoadingDialog(requireActivity());
    }

    private void initViewmodel() {
        authViewmodel = new ViewModelProvider(requireActivity()).get(AuthViewmodel.class);
    }


    private void hideKeyPad() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @OnClick(R.id.signUpText)
    void signUpTextClicked() {
        NavHostFragment.findNavController(this)
                .navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment());
    }

    @OnClick(R.id.signInButton)
    void signInButtonClicked() {
        hideKeyPad();
        String mobile = phnNumberET.getText().toString();
        String password = passwordET.getText().toString();

        if (isInputValid(mobile, password)) {
            authViewmodel.userSignIn(new AuthSignIn(mobile, password), this::isRestCallCompleted);
            customLoadingDialog.startLoadingDialog();
            authViewmodel.authResponseSignIn.observe(getViewLifecycleOwner(), authResponse -> {
                if (authResponse != null) {
                    if (authResponse.status) {
                        SessionManager.getInstance(requireActivity())
                                .saveSubId(mobile);
                        NavHostFragment.findNavController(this)
                                .navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment());
                    } else {
                        UtilClass.showSnackBar(signInLayout, authResponse.msg);
                    }
                } else {
                    UtilClass.showSnackBar(signInLayout, "Something went wrong! Please try again.");
                }
            });
        } else {
            UtilClass.showSnackBar(signInLayout, "Please provide valid input");
        }
    }

    private boolean isInputValid(String mobile, String password) {
        if (!(mobile.isEmpty() && password.isEmpty())) {
            return mobile.startsWith("01") && mobile.length() == 11;
        }
        return false;
    }

    @Override
    public void isRestCallCompleted(boolean isCompleted) {
        if (isCompleted) customLoadingDialog.stopLoadingDialog();
    }
}
