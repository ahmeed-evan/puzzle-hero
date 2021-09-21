package com.innovertech.puzzlehero.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResult;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PuzzleResultFragment extends Fragment {

    @BindView(R.id.titleText)
    TextView titleText;
    @BindView(R.id.puzzleResultImg)
    ImageView puzzleResultImg;
    @BindView(R.id.resultText)
    TextView resultText;
    @BindView(R.id.pointText)
    TextView pointText;

    private QuestionResult questionResult;
    private String puzzleCategory;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view
                = inflater.inflate(R.layout.fragment_puzzle_result, container, false);
        ButterKnife.bind(this, view);
        getArgs();
        return view;
    }

    private void getArgs() {
        questionResult = PuzzleResultFragmentArgs.fromBundle(getArguments()).getQuestionResult();
        puzzleCategory = PuzzleResultFragmentArgs.fromBundle(getArguments()).getPuzzleCategory();
        if (questionResult != null) {
            setUpScreen(questionResult, puzzleCategory);
        }
    }

    private void setUpScreen(QuestionResult questionResult, String puzzleCategory) {
        if (puzzleCategory.equals("1")) {
            titleText.setText("Visual Puzzle");
        } else {
            titleText.setText("General Knowledge");
        }
        titleText.setBackgroundResource(R.drawable.bg_custom_titile_box_yellow);
        if (questionResult.isCorrect) {
            puzzleResultImg.setBackgroundResource(R.drawable.right_answer_img);
            resultText.setText("Your Answer is Right");
        } else {
            puzzleResultImg.setBackgroundResource(R.drawable.wrong_answer_img);
            resultText.setText("Your Answer is Wrong");
        }
        resultText.setBackgroundResource(R.drawable.bg_custom_titile_box_yellow);
        pointText.setText(String.valueOf(questionResult.totalPoint));
    }

    @OnClick(R.id.backButton)
    void backClicked() {
        NavHostFragment.findNavController(this)
                .navigate(PuzzleFragmentDirections.backHome());
    }

    @OnClick(R.id.nextButton)
    void onNextButtonClicked() {
        NavHostFragment.findNavController(this)
                .navigate(PuzzleResultFragmentDirections.actionPuzzleResultFragmentToPuzzleFragment(puzzleCategory));
    }

}
