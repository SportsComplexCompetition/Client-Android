package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.Competition;

import java.util.ArrayList;

public class ManageCompetition {
    private  static ManageCompetition manageCompetition=null;
    Competition competition;
    private ArrayList<Competition> event_total;
    private ArrayList<Competition> match_total;
    public static ManageCompetition getInstance(){
        if(manageCompetition==null){
            manageCompetition=new ManageCompetition();
        }
        return manageCompetition;
    }

    private ManageCompetition(){
        competition=new Competition();
        event_total=new ArrayList<>();
        match_total=new ArrayList<>();
    }


    public void setEvent_total(ArrayList<Competition> event_total) {
        this.event_total = event_total;
    }

    public ArrayList<Competition> getEvent_total() {
        return event_total;
    }

    public void setMatch_total(ArrayList<Competition> match_total) {
        this.match_total = match_total;
    }

    public ArrayList<Competition> getMatch_total() {
        return match_total;
    }

    public void addnewEvent(Competition competition){
        event_total.add(competition);
    }
    public void addnewMatch(Competition competition){
        match_total.add(competition);
    }

}
