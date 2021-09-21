package com.innovertech.puzzlehero.model.ScoreHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreResponse {
    @SerializedName("msisdn_found")
    @Expose
    public String msisdnFound;
    @SerializedName("result")
    @Expose
    public Result result;
}
