package com.innovertech.puzzlehero.model.Leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardResponse {
    @SerializedName("list")
    @Expose
    public List<Leaders> leadersList = null;
    @SerializedName("this_month")
    @Expose
    public String thisMonth;
    @SerializedName("my_pos")
    @Expose
    public Integer myPos;
    @SerializedName("my_points")
    @Expose
    public Integer myPoints;
}
