package com.innovertech.puzzlehero.model.ScoreHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @SerializedName("this_month")
    @Expose
    public String thisMonth;
    @SerializedName("wrong")
    @Expose
    public Integer wrong;
    @SerializedName("correct")
    @Expose
    public Integer correct;
    @SerializedName("total_point")
    @Expose
    public Integer totalPoint;
    @SerializedName("this_month_wrong")
    @Expose
    public Integer thisMonthWrong;
    @SerializedName("this_month_correct")
    @Expose
    public Integer thisMonthCorrect;
    @SerializedName("this_month_total_point")
    @Expose
    public Integer thisMonthTotalPoint;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("this_month_total")
    @Expose
    public Integer thisMonthTotal;
}
