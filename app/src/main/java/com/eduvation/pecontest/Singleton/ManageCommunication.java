package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.Communication;

import java.util.ArrayList;

public class ManageCommunication {
    private static ManageCommunication manageCommunication=null;
    Communication communication;
    private ArrayList<Communication> communication_total;
    public static ManageCommunication getInstance(){
        if(manageCommunication==null){
            manageCommunication=new ManageCommunication();
        }
        return manageCommunication;
    }

    private ManageCommunication(){
        communication=new Communication();
        communication_total=new ArrayList<>();
    }

    public ArrayList<Communication> getCommunication_total() {
        return communication_total;
    }

    public void setCommunication_total(ArrayList<Communication> communication_total) {
        this.communication_total = communication_total;
    }

    public void addnewCommunication(Communication communication){
        communication_total.add(communication);
    }
}
