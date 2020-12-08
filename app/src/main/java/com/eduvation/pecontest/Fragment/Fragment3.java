package com.eduvation.pecontest.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Activity.AddMatchActivity;
import com.eduvation.pecontest.Adapter.EventAdapter;
import com.eduvation.pecontest.Adapter.MatchAdapter;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageCompetition;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    RecyclerView event_recycler, match_recycler;
    ImageView add_match;
    ImageView no_match_img, no_event_img;
    EventAdapter eventAdapter;
    public MatchAdapter matchAdapter;
    ArrayList<Competition> events=null;
    ArrayList<Competition> matches=null;
    ArrayList<Integer> eventimg=null;
    ArrayList<Integer> matchimg=null;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment3, container, false);

        event_recycler=v.findViewById(R.id.event_recycler);
        match_recycler=v.findViewById(R.id.my_match_recycler);
        add_match=v.findViewById(R.id.add_match_btn);
        no_event_img=v.findViewById(R.id.no_event_img);
        no_match_img=v.findViewById(R.id.no_match_img);
        context=getContext();

        setting_recyclerview();

        setting_make_new_competition();

        return v;
    }

    public void setting_recyclerview(){
        events=new ArrayList<>();
        events= ManageCompetition.getInstance().getEvent_total();
        matches=new ArrayList<>();
        matches=ManageCompetition.getInstance().getMatch_total();
        matchimg=new ArrayList<>();
        matchimg.add(R.drawable.match1);
        matchimg.add(R.drawable.match2);
        matchimg.add(R.drawable.match3);
        matchimg.add(R.drawable.match4);
        eventimg=new ArrayList<>();
        eventimg.add(R.drawable.photo1);
        eventimg.add(R.drawable.photo2);
        eventimg.add(R.drawable.photo3);
        eventimg.add(R.drawable.photo4);

        event_recycler.setLayoutManager(new LinearLayoutManager(context));
        eventAdapter=new EventAdapter(events, eventimg, context);
        event_recycler.setAdapter(eventAdapter);
        if(events.size()==0){
            no_event_img.setVisibility(View.VISIBLE);
        }

        match_recycler.setLayoutManager(new GridLayoutManager(context, 2));
        matchAdapter=new MatchAdapter(matches, matchimg, context);
        match_recycler.setAdapter(matchAdapter);
        if(matches.size()==0){
            no_match_img.setVisibility(View.VISIBLE);
        }

    }

    public void addnewMatch(){
        if(matchAdapter.getItemCount()>=1){
            no_match_img.setVisibility(View.GONE);
        }
    }

    public void setting_make_new_competition(){
        add_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddMatchActivity.class));
            }
        });
    }
}
