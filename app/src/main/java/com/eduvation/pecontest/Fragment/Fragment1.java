package com.eduvation.pecontest.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Adapter.Main_Challenge_Adapter;
import com.eduvation.pecontest.Class.Area_Rank_Data;
import com.eduvation.pecontest.Class.Main_Challenge;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {
    RecyclerView challenge;
    ArrayList<Main_Challenge> data=null;
    Main_Challenge_Adapter adapter=null;
    Context context;

    RetrofitAPI myAPI;
    ArrayList<Area_Rank_Data> rank_data=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);

        challenge=v.findViewById(R.id.main_challenge_recycle);
        context=getContext();


        setting_data();
        setting_recyclerview();

        getting_data_from_server();


        return v;
    }

    public void setting_data(){
        data=new ArrayList<>();

        Main_Challenge m=new Main_Challenge("푸쉬업", "12월 3일~12월 31일", 102, R.drawable.main_challenge1);
        data.add(m);

        rank_data=new ArrayList<>();
    }

    public void setting_recyclerview(){
        challenge.setLayoutManager(new LinearLayoutManager(context));
        adapter=new Main_Challenge_Adapter(data, context);
        challenge.setAdapter(adapter);
    }

    public void getting_data_from_server(){
        myAPI= RetrofitClient.getApiService();
        Call<JsonArray> getrank=myAPI.get_medal_rate();
        getrank.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray=new JsonArray();
                    jsonArray=response.body();
                    for(int i=0; i<jsonArray.size(); i++){
                        JsonElement each=jsonArray.get(i);
                        JsonArray eacharray=each.getAsJsonArray();
                        String location=eacharray.get(0).toString();
                        location=location.replaceAll("\"", "");
                        JsonElement medal=eacharray.get(1);
                        JsonArray medalarray=medal.getAsJsonArray();
                        String gold=medalarray.get(0).toString();
                        String silver=medalarray.get(1).toString();
                        String bronze=medalarray.get(2).toString();
                        rank_data.add(new Area_Rank_Data(location, gold, silver, bronze, "0"));
                    }
//                    for(int i=0; i<rank_data.size(); i++){
//                        System.out.println(rank_data.get(i).getLocation()+" "+rank_data.get(i).getGold()+" "+rank_data.get(i).getSilver()+" "+rank_data.get(i).getBronze()+" "+rank_data.get(i).getMatch());
//                    }
                }
            }
            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                System.out.println(t);
                System.out.println("Fail");
            }
        });
    }
}
