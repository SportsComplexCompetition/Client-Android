package com.eduvation.pecontest.Network;

import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Class.Communication;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.NewAttend;
import com.eduvation.pecontest.Class.PE_average;
import com.eduvation.pecontest.Class.Total_score;
import com.eduvation.pecontest.Class.User;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("data/local-rank/")
    Call<JsonArray> get_medal_rate();

    @GET("meetinglist/")
    Call<ArrayList<Communication>> get_communication();

    @POST("meetinglist/")
    Call<Void> make_communication(@Body Communication meeting);

    @GET("commentlist/")
    Call<ArrayList<Comment>> get_comment();

    @POST("commentlist/")
    Call<Void> send_comment(@Body Comment comment);

    @GET("complist/")
    Call<ArrayList<Competition>> get_competition();

    @POST("complist/")
    Call<Void> make_competition(@Body Competition competition);

    @PUT("competitions/join/{pk}")
    Call<Void> join_competition(@Path("pk") int pk, @Body NewAttend newAttend);

    @GET("userlist/")
    Call<ArrayList<User>> get_user();

    @GET("data/average/")
    Call<JsonArray> get_average();

    @GET("data/topavglow/")
    Call<ArrayList<Total_score>> get_topavglow();
}
