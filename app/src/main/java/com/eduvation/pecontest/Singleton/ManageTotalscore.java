package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.Total_score;

import java.util.ArrayList;

public class ManageTotalscore {
    private static ManageTotalscore manageTotalscore=null;
    Total_score score;
    ArrayList<Total_score> total;
    public static ManageTotalscore getInstance(){
        if(manageTotalscore==null){
            manageTotalscore=new ManageTotalscore();
        }
        return manageTotalscore;
    }
    public ManageTotalscore(){
        score=new Total_score();
        total=new ArrayList<>();
    }

    public ArrayList<Total_score> getTotal() {
        return total;
    }

    public void setTotal(ArrayList<Total_score> total) {
        this.total = total;
    }
}
