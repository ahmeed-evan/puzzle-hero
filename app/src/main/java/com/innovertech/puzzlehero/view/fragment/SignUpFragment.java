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
import com.innovertech.puzzlehero.model.Auth.AuthSignUp;
import com.innovertech.puzzlehero.util.CustomLoadingDialog;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.SessionManager;
import com.innovertech.puzzlehero.util.UtilClass;
import com.innovertech.puzzlehero.viewmodel.AuthViewmodel;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment implements RestProgressCallback {

    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.phnNumberET)
    EditText phnNumberET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.confirmPasswordET)
    EditText confirmPasswordET;
    @BindView(R.id.signUpLayout)
    ConstraintLayout signUpLayout;

    private AuthViewmodel authViewmodel;
    private CustomLoadingDialog customLoadingDialog;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view
                = inflater.inflate(R.layout.fragment_sign_up, container, false);
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

    @OnClick(R.id.signUpButton)
    void signUpButtonClicked() {
        hideKeyPad();
        String userName = nameET.getText().toString();
        String mobile = phnNumberET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        if (isPasswordMatched(password, confirmPassword)) {
            if (isInputValid(userName, mobile, password)) {
                authViewmodel.userSignUp(new AuthSignUp(userName, mobile, password), this::isRestCallCompleted);
                customLoadingDialog.startLoadingDialog();
                authViewmodel.authResponseSignUp.observe(getViewLifecycleOwner(), authResponse -> {
                    if (authResponse != null) {
                        if (authResponse.status) {
                            SessionManager.getInstance(requireActivity())
                                    .saveSubId(mobile);
                            NavHostFragment.findNavController(this)
                                    .navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment());
                        } else {
                            UtilClass.showSnackBar(signUpLayout, authResponse.msg);
                        }
                    } else {
                        UtilClass.showSnackBar(signUpLayout, "Something went wrong! Please try again.");
                    }
                });
            } else {
                UtilClass.showSnackBar(signUpLayout, "Please provide valid input");
            }
        } else {
            UtilClass.showSnackBar(signUpLayout, "Password did not match. Please check your input value");
        }
    }

    private boolean isInputValid(String userName, String mobile, String password) {
        if (!(userName.isEmpty() && mobile.isEmpty() && password.isEmpty())) {
            return mobile.startsWith("01") && mobile.length() == 11;
        }
        return false;
    }

    private boolean isPasswordMatched(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @OnClick(R.id.alreadyHaveAccountText)
    void alreadyHaveAccountTextClicked() {
        NavHostFragment.findNavController(this)
                .popBackStack();
    }

    @Override
    public void isRestCallCompleted(boolean isCompleted) {
        if (isCompleted) customLoadingDialog.stopLoadingDialog();
    }
}
