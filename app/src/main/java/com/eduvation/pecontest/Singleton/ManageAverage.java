package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.PE_average;

import java.util.ArrayList;

public class ManageAverage {
    private static ManageAverage manageAverage=null;
    PE_average pe_average;
    public static ManageAverage getInstance(){
        if(manageAverage==null){
            manageAverage=new ManageAverage();
        }
        return manageAverage;
    }
    public ManageAverage(){
        pe_average=new PE_average();
    }

    public PE_average getPe_average() {
        return pe_average;
    }

    public void setPe_average(PE_average pe_average) {
        this.pe_average = pe_average;
    }
}
