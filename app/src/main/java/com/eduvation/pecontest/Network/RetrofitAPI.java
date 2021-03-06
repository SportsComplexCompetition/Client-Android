package com.eduvation.pecontest.Network;

import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Class.Communication;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.Login;
import com.eduvation.pecontest.Class.Login_User;
import com.eduvation.pecontest.Class.MyCompetition;
import com.eduvation.pecontest.Class.NewAttend;
import com.eduvation.pecontest.Class.Register;
import com.eduvation.pecontest.Class.Total_score;
import com.eduvation.pecontest.Class.User;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
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

    @POST("commentlist/")
    Call<Void> send_comment(@Body Comment comment);

    @GET("complist/")
    Call<ArrayList<Competition>> get_competition();

    @POST("complist/")
    Call<Void> make_competition(@Body Competition competition);

    @PUT("competitions/join/{pk}")
    Call<Void> join_competition(@Path("pk") int pk, @Body NewAttend newAttend);

    @GET("data/average/")
    Call<JsonArray> get_average();

    @GET("data/topavglow/")
    Call<ArrayList<Total_score>> get_topavglow();

    @POST("accounts/signin/")
    Call<User> login(@Body Login login);

    @POST("accounts/signup/")
    Call<Void> signup(@Body Register register);

    @GET("userlist/{pk}/")
    Call<Login_User> get_login_user(@Header("Authorization") String header, @Path("pk") int pk);
    //need Header token authorization
    //if there's no header token, get 401 code error

    @GET("accounts/mycomplist")
    Call<ArrayList<MyCompetition>> get_my_competition(@Header("Authorization") String header);
}
