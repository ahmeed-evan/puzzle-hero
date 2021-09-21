package com.innovertech.puzzlehero.model.PuzzleQuestion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    @SerializedName("msisdn_found")
    @Expose
    public String msisdnFound;
    @SerializedName("isSubscribed")
    @Expose
    public Boolean isSubscribed;
    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("question_bangla")
    @Expose
    public String questionBangla;
    @SerializedName("option1_bangla")
    @Expose
    public String option1Bangla;
    @SerializedName("option2_bangla")
    @Expose
    public String option2Bangla;
    @SerializedName("option3_bangla")
    @Expose
    public String option3Bangla;
    @SerializedName("option4_bangla")
    @Expose
    public String option4Bangla;
    @SerializedName("subcategory_id")
    @Expose
    public Integer subcategoryId;
    @SerializedName("image_link")
    @Expose
    public String imageLink;
    @SerializedName("category_name")
    @Expose
    public String categoryName;
    @SerializedName("total_point")
    @Expose
    public Integer totalPoint;
    @SerializedName("logo_path")
    @Expose
    public String logoPath;
}
