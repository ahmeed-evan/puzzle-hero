package com.innovertech.puzzlehero.model.Leaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leaders {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("rank")
    @Expose
    public Integer rank;
}
