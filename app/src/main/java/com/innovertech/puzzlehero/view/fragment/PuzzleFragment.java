package com.innovertech.puzzlehero.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResponse;
import com.innovertech.puzzlehero.model.PuzzleQuestion.SubmitAnswer;
import com.innovertech.puzzlehero.util.CustomLoadingDialog;
import com.innovertech.puzzlehero.util.RestProgressCallback;
import com.innovertech.puzzlehero.util.UtilClass;
import com.innovertech.puzzlehero.viewmodel.PuzzleViewmodel;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class PuzzleFragment extends Fragment implements RestProgressCallback {

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.puzzleText)
    TextView puzzleText;
    @BindView(R.id.optionOne)
    AppCompatButton optionOne;
    @BindView(R.id.optionTwo)
    AppCompatButton optionTwo;
    @BindView(R.id.optionThree)
    AppCompatButton optionThree;
    @BindView(R.id.optionFour)
    AppCompatButton optionFour;
    @BindView(R.id.nextButton)
    AppCompatButton nextButton;
    @BindView(R.id.puzzleImage)
    ImageView puzzleImage;
    @BindView(R.id.puzzleLayout)
    ConstraintLayout puzzleLayout;

    private String puzzleCategory;
    private CustomLoadingDialog customLoadingDialog;
    private PuzzleViewmodel puzzleViewmodel;
    private String selectedOption = "";
    QuestionResponse questionResponse;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view
                = inflater.inflate(R.layout.fragment_puzzle, container, false);
        ButterKnife.bind(this, view);
        getArgs();
        initDialog();
        initViewmodel();
        getQuestion();
        getActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return view;
    }

    private void getQuestion() {
        puzzleViewmodel.getQuestion(puzzleCategory, this);
        customLoadingDialog.startLoadingDialog();
        setQuestionToScreen();
    }

    private void setQuestionToScreen() {
        puzzleViewmodel.questionResponseLiveData.observe(getViewLifecycleOwner(), questionRes -> {
            if (questionRes != null) {
                if (questionRes.success) {
                    questionResponse = questionRes;
                    setImg();
                    puzzleText.setText(questionResponse.questionBangla);
                    puzzleText.setBackgroundColor(Color.parseColor("#B328731C"));
                    optionOne.setText(questionResponse.option1Bangla);
                    optionOne.setBackgroundResource(R.drawable.bg_custom_option_button);
                    optionTwo.setText(questionResponse.option2Bangla);
                    optionTwo.setBackgroundResource(R.drawable.bg_custom_option_button);
                    optionThree.setText(questionResponse.option3Bangla);
                    optionThree.setBackgroundResource(R.drawable.bg_custom_option_button);
                    optionFour.setText(questionResponse.option4Bangla);
                    optionFour.setBackgroundResource(R.drawable.bg_custom_option_button);
                } else {
                    UtilClass.showSnackBar(puzzleLayout, "You have exceeded your daily quiz quota of today. Please try tomorrow.");
                }
            } else {
                UtilClass.showSnackBar(puzzleLayout, "Something went wrong! Please try again.");
            }
        });
    }

    private void setImg() {
        new Handler(Looper.myLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(requireActivity()).load(questionResponse.imageLink).into(puzzleImage);
                    }
                });
    }

    private void initViewmodel() {
        puzzleViewmodel = new ViewModelProvider(requireActivity()).get(PuzzleViewmodel.class);
    }

    private void initDialog() {
        customLoadingDialog = new CustomLoadingDialog(requireActivity());
    }

    private void getArgs() {
        puzzleCategory = PuzzleFragmentArgs.fromBundle(getArguments()).getPuzzleCategory();
        setTitle(puzzleCategory);
    }

    private void setTitle(String puzzleCategory) {
        if (puzzleCategory.equals("1")) {
            titleText.setText("Visual Puzzle");
        } else {
            titleText.setText("General Knowledge");
        }
    }

    @Override
    public void isRestCallCompleted(boolean isCompleted) {
        if (isCompleted) customLoadingDialog.stopLoadingDialog();
    }

    @OnClick({R.id.optionOne, R.id.optionTwo, R.id.optionThree, R.id.optionFour})
    void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.optionOne:
                optionOneClicked();
                break;
            case R.id.optionTwo:
                optionTwoClicked();
                break;
            case R.id.optionThree:
                optionThreeClicked();
                break;
            case R.id.optionFour:
                optionFourClicked();
                break;

        }
    }

    private void optionFourClicked() {
        Log.d(TAG, "optionFourClicked: ");
        nextButton.setVisibility(View.VISIBLE);
        selectedOption = questionResponse.option4Bangla;
        optionOne.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionTwo.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionThree.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionFour.setBackgroundResource(R.drawable.bg_custom_option_button_clicked);
    }

    private void optionThreeClicked() {
        Log.d(TAG, "optionThreeClicked: ");
        nextButton.setVisibility(View.VISIBLE);
        selectedOption = questionResponse.option3Bangla;
        optionOne.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionTwo.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionThree.setBackgroundResource(R.drawable.bg_custom_option_button_clicked);
        optionFour.setBackgroundResource(R.drawable.bg_custom_option_button);
    }

    private void optionTwoClicked() {
        Log.d(TAG, "optionTwoClicked: ");
        nextButton.setVisibility(View.VISIBLE);
        selectedOption = questionResponse.option2Bangla;
        optionOne.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionTwo.setBackgroundResource(R.drawable.bg_custom_option_button_clicked);
        optionThree.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionFour.setBackgroundResource(R.drawable.bg_custom_option_button);
    }

    private void optionOneClicked() {
        Log.d(TAG, "optionOneClicked: ");
        nextButton.setVisibility(View.VISIBLE);
        selectedOption = questionResponse.option1Bangla;
        optionOne.setBackgroundResource(R.drawable.bg_custom_option_button_clicked);
        optionTwo.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionThree.setBackgroundResource(R.drawable.bg_custom_option_button);
        optionFour.setBackgroundResource(R.drawable.bg_custom_option_button);
    }

    @OnClick(R.id.nextButton)
    void onNextButtonClicked() {
        puzzleViewmodel.submitAnswer(new SubmitAnswer(
                        questionResponse.id, questionResponse.subcategoryId, selectedOption),
                this::isRestCallCompleted);
        customLoadingDialog.startLoadingDialog();
        goResultScreen();
    }

    private void goResultScreen() {
        puzzleViewmodel.answerResultLiveData.observe(getViewLifecycleOwner(), questionResult -> {
            if (questionResult != null) {
                if (questionResult.success) {
                    NavHostFragment.findNavController(this)
                            .navigate(PuzzleFragmentDirections
                                    .actionPuzzleFragmentToPuzzleResultFragment(questionResult, String.valueOf(questionResponse.subcategoryId)));
                }
            }
        });
    }

    @OnClick(R.id.backArrow)
    void backClicked() {
        NavHostFragment.findNavController(this)
                .navigate(PuzzleFragmentDirections.backHome());
    }

    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            /*
             *do nothing
             * */
        }
    };
}
