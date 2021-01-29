package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.MyCompetition;

import java.util.ArrayList;

public class ManageMyCompetition {
    private static ManageMyCompetition manageMyCompetition=null;
    MyCompetition myCompetition;
    private ArrayList<MyCompetition> myCompetition_total;
    public static ManageMyCompetition getInstance(){
        if(manageMyCompetition==null){
            manageMyCompetition=new ManageMyCompetition();
        }
        return manageMyCompetition;
    }

    private ManageMyCompetition(){
        myCompetition=new MyCompetition();
        myCompetition_total=new ArrayList<>();
    }

    public void setMyCompetition_total(ArrayList<MyCompetition> myCompetition_total) {
        this.myCompetition_total = myCompetition_total;
    }

    public ArrayList<MyCompetition> getMyCompetition_total() {
        return myCompetition_total;
    }
}
