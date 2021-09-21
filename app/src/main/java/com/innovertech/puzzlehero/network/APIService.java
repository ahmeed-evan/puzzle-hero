package com.innovertech.puzzlehero.network;

import com.innovertech.puzzlehero.model.Auth.AuthResponse;
import com.innovertech.puzzlehero.model.Auth.AuthSignIn;
import com.innovertech.puzzlehero.model.Auth.AuthSignUp;
import com.innovertech.puzzlehero.model.Leaderboard.LeaderboardResponse;
import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResponse;
import com.innovertech.puzzlehero.model.PuzzleQuestion.QuestionResult;
import com.innovertech.puzzlehero.model.PuzzleQuestion.SubmitAnswer;
import com.innovertech.puzzlehero.model.ScoreHistory.ScoreResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @Headers("Content-Type: text/html; charset=utf-8")
    @POST("Login/login")
    Call<AuthResponse> userSignIn(@Body AuthSignIn authSignIn);

    @Headers("Content-Type: text/html; charset=utf-8")
    @POST("Login/addProfile")
    Call<AuthResponse> userSignUp(@Body AuthSignUp authSignUp);

    @Headers("Content-Type: text/html; charset=utf-8")
    @GET("Get_question/index/{puzzleCategory}/1/{subId}")
    Call<QuestionResponse> getQuestion(@Path("puzzleCategory") String puzzleCategory, @Path("subId") String subId);

    @Headers("Content-Type: text/html; charset=utf-8")
    @POST("Submit_answer/index/1/{subId}")
    Call<QuestionResult> getResult(@Body SubmitAnswer submitAnswer, @Path("subId") String subId);

    @Headers("Content-Type: text/html; charset=utf-8")
    @GET("Get_user_history/index/1/{subId}")
    Call<ScoreResponse> getScoreHistory(@Path("subId") String subId);

    @Headers("Content-Type: text/html; charset=utf-8")
    @GET("Get_user_history/getLeaderboard/1/{subId}")
    Call<LeaderboardResponse> getLeaders(@Path("subId") String subId);
}
