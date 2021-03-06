package com.eduvation.pecontest.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Activity.AddMeetingActivity;
import com.eduvation.pecontest.Adapter.Communicate_Adapter;
import com.eduvation.pecontest.Class.Communication;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageCommunication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment2 extends Fragment {
    SearchView search;
    Spinner loc_spinner, pe_spinner;
    RecyclerView commute_recycle;
    ImageView add_btn;
    Context context;
    public Communicate_Adapter adapter;
    ArrayList<Integer> back_img=null;
    ArrayList<Communication> total=null;
    ArrayList<Communication> choose=null;
    String loc, pe;

    RetrofitAPI myAPI;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment2, container, false);

        search=v.findViewById(R.id.search);
        search.onActionViewExpanded();
        loc_spinner=v.findViewById(R.id.location_spinner);
        pe_spinner=v.findViewById(R.id.pe_spinner);
        commute_recycle=v.findViewById(R.id.commute_recycler);
        add_btn=v.findViewById(R.id.add_meeting_btn);
        context=getContext();

        setting_data();
        setting_spinner();
        setting_click();

        return v;
    }

    public void setting_data(){
        back_img=new ArrayList<>();
        back_img.add(R.drawable.photo1);
        back_img.add(R.drawable.photo2);
        back_img.add(R.drawable.photo3);
        back_img.add(R.drawable.photo4);

        total=new ArrayList<>();
        total= ManageCommunication.getInstance().getCommunication_total();
        setting_recyclerview();
        myAPI= RetrofitClient.getApiService();

        choose=new ArrayList<>();
    }
    public void setting_recyclerview(){
        commute_recycle.setLayoutManager(new LinearLayoutManager(context));
        adapter=new Communicate_Adapter(total, back_img, context);
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

    public void setting_click(){
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddMeetingActivity.class));
            }
        });
    }
}
