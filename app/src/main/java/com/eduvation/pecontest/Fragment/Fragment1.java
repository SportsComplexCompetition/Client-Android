package com.eduvation.pecontest.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Adapter.Main_Challenge_Adapter;
import com.eduvation.pecontest.Class.Main_Challenge;
import com.eduvation.pecontest.R;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    RecyclerView challenge;
    ArrayList<Main_Challenge> data=null;
    Main_Challenge_Adapter adapter=null;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);

        challenge=v.findViewById(R.id.main_challenge_recycle);
        context=getContext();

        setting_data();
        setting_recyclerview();

        return v;
    }

    public void setting_data(){
        data=new ArrayList<>();

        Main_Challenge m=new Main_Challenge("푸쉬업", "12월 3일~12월 31일", 102, R.drawable.main_challenge1);
        data.add(m);

    }

    public void setting_recyclerview(){
        challenge.setLayoutManager(new LinearLayoutManager(context));
        adapter=new Main_Challenge_Adapter(data, context);
        challenge.setAdapter(adapter);
    }
}
