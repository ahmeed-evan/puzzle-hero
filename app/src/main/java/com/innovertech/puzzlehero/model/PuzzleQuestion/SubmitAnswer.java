package com.innovertech.puzzlehero.model.PuzzleQuestion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAnswer {
    @SerializedName("question_id")
    @Expose
    public Integer questionId;
    @SerializedName("sub_category_id")
    @Expose
    public Integer subCategoryId;
    @SerializedName("answer")
    @Expose
    public String answer;
}
