package com.eduvation.pecontest.Network;

import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Class.Communication;
import com.google.gson.JsonArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
