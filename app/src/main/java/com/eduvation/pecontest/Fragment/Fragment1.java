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
import com.eduvation.pecontest.Adapter.Main_Rank_Adapter;
import com.eduvation.pecontest.Class.Area_Rank_Data;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.Main_Challenge;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageCompetition;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {
    TextView seoul, daegu, daejun, gwangjoo, inchun, pusan, ulsan, sejong, jeju, kunggi, kanwon, chungnam, chungbuk, junnam, junbuk, kyungnam, kyungbuk;

    RecyclerView challenge;
    ArrayList<Competition> data=null;
    Main_Challenge_Adapter adapter=null;
    Context context;
    RecyclerView rank;
    Main_Rank_Adapter rankadapter=null;

    RetrofitAPI myAPI;
    ArrayList<Area_Rank_Data> rank_data=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);

        challenge=v.findViewById(R.id.main_challenge_recycle);
        rank=v.findViewById(R.id.main_rank_recycle);
        seoul=v.findViewById(R.id.seoul);
        daegu=v.findViewById(R.id.daegu);
        daejun=v.findViewById(R.id.daejun);
        gwangjoo=v.findViewById(R.id.gwangjoo);
        inchun=v.findViewById(R.id.inchun);
        pusan=v.findViewById(R.id.pusan);
        ulsan=v.findViewById(R.id.ulsan);
        sejong=v.findViewById(R.id.sejong);
        jeju=v.findViewById(R.id.jeju);
        kunggi=v.findViewById(R.id.kunggi);
        kanwon=v.findViewById(R.id.kangwon);
        chungnam=v.findViewById(R.id.chungnam);
        chungbuk=v.findViewById(R.id.chungbuk);
        junnam=v.findViewById(R.id.junnam);
        junbuk=v.findViewById(R.id.junbuk);
        kyungnam=v.findViewById(R.id.kyungnam);
        kyungbuk=v.findViewById(R.id.kyungbuk);



        context=getContext();


        setting_data();
        setting_recyclerview();

        getting_data_from_server();


        return v;
    }

    public void setting_data(){
        data=new ArrayList<>();


        data.add(ManageCompetition.getInstance().getBest());
        ArrayList<Integer> count=new ArrayList<>();
        count=ManageCompetition.getInstance().getMatch_count();
        seoul.setText(Integer.toString(count.get(0)));
        daegu.setText(Integer.toString(count.get(1)));
        daejun.setText(Integer.toString(count.get(2)));
        gwangjoo.setText(Integer.toString(count.get(3)));
        inchun.setText(Integer.toString(count.get(4)));
        pusan.setText(Integer.toString(count.get(5)));
        ulsan.setText(Integer.toString(count.get(6)));
        sejong.setText(Integer.toString(count.get(7)));
        jeju.setText(Integer.toString(count.get(8)));
        kunggi.setText(Integer.toString(count.get(9)));
        kanwon.setText(Integer.toString(count.get(10)));
        chungnam.setText(Integer.toString(count.get(11)));
        chungbuk.setText(Integer.toString(count.get(12)));
        junnam.setText(Integer.toString(count.get(13)));
        junbuk.setText(Integer.toString(count.get(14)));
        kyungnam.setText(Integer.toString(count.get(15)));
        kyungbuk.setText(Integer.toString(count.get(16)));

        rank_data=new ArrayList<>();
    }

    public void setting_recyclerview(){
        challenge.setLayoutManager(new LinearLayoutManager(context));
        adapter=new Main_Challenge_Adapter(data, context);
        challenge.setAdapter(adapter);

        rank.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
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
                    rankadapter=new Main_Rank_Adapter(rank_data, context);
                    rank.setAdapter(rankadapter);
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
