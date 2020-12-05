package com.eduvation.pecontest.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Adapter.Communicate_Adapter;
import com.eduvation.pecontest.Class.Communication;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {
    SearchView search;
    Spinner loc_spinner, pe_spinner;
    RecyclerView commute_recycle;
    Context context;
    Communicate_Adapter adapter;
    ArrayList<Communication> total=null;
    ArrayList<Communication> choose=null;
    String loc, pe;

    RetrofitAPI myAPI;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment2, container, false);

        search=v.findViewById(R.id.search);
        loc_spinner=v.findViewById(R.id.location_spinner);
        pe_spinner=v.findViewById(R.id.pe_spinner);
        commute_recycle=v.findViewById(R.id.commute_recycler);
        context=getContext();

        setting_data();
        setting_spinner();

        return v;
    }

    public void setting_data(){
        total=new ArrayList<>();
        myAPI= RetrofitClient.getApiService();
        Call<ArrayList<Communication>> getcommute=myAPI.get_communication();
        getcommute.enqueue(new Callback<ArrayList<Communication>>() {
            @Override
            public void onResponse(Call<ArrayList<Communication>> call, Response<ArrayList<Communication>> response) {
                if(response.isSuccessful()){
                    for(Communication item:response.body()){
                        total.add(new Communication(item.getHost_email(), item.getHost_key(), item.getLocation(), item.getTitle(), item.getPeople(), item.getBody(), item.getDate()));
                    }
                    setting_recyclerview();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Communication>> call, Throwable t) {
                System.out.println("communication fail");
            }
        });
        choose=new ArrayList<>();
    }
    public void setting_recyclerview(){
        commute_recycle.setLayoutManager(new LinearLayoutManager(context));
        adapter=new Communicate_Adapter(total, context);
        commute_recycle.setAdapter(adapter);
    }

    public void setting_spinner(){
        loc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc=loc_spinner.getSelectedItem().toString();
                pe=pe_spinner.getSelectedItem().toString();
                if(choose!=null){
                    choose.clear();
                }
                find_want_communication();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        pe_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pe=pe_spinner.getSelectedItem().toString();
                loc=loc_spinner.getSelectedItem().toString();
                if(choose!=null){
                    choose.clear();
                }
                find_want_communication();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void find_want_communication(){
//        if(loc.equals("지역선택")&&pe.equals("종목선택")){
//            choose=total;
//        }
//        else if(loc.equals("지역선택")&&(!pe.equals("종목선택"))){
//            for(int i=0; i<total.size(); i++){
//                if(pe.equals(total.get(i).getCategory())){
//                    choose.add(total.get(i));
//                }
//            }
//        }
//        else if((!loc.equals("지역선택"))&&pe.equals("종목선택")){
//            for(int i=0; i<total.size(); i++){
//                if(loc.equals(total.get(i).getLocation())){
//                    choose.add(total.get(i));
//                }
//            }
//        }
//        else{
//            for(int i=0; i<total.size(); i++){
//                if(loc.equals(total.get(i).getLocation())&&pe.equals(total.get(i).getCategory())){
//                    choose.add(total.get(i));
//                }
//            }
//        }
//        adapter.removeall(choose);
    }
}
