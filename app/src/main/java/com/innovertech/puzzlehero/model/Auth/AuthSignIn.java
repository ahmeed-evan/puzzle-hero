package com.innovertech.puzzlehero.model.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthSignIn {

    @SerializedName("msisdn")
    @Expose
    public String msisdn;
    @SerializedName("password")
    @Expose
    public String password;
}
