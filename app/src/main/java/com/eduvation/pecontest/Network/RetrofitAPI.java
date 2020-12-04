package com.eduvation.pecontest.Network;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("data/local-rank/")
    Call<JsonArray> get_medal_rate();
}
