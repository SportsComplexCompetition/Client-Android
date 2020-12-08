package com.eduvation.pecontest.Singleton;


import com.eduvation.pecontest.Class.EachCert;

import java.util.ArrayList;

public class ManageCert {
    private  static ManageCert manageCert=null;
    EachCert eachCert;
    private ArrayList<EachCert> cert_total;
    public static ManageCert getInstance(){
        if(manageCert==null){
            manageCert=new ManageCert();
        }
        return manageCert;
    }
    private ManageCert(){
        eachCert=new EachCert();
        cert_total=new ArrayList<>();
    }

    public void setCert_total(ArrayList<EachCert> cert_total) {
        this.cert_total = cert_total;
    }

    public ArrayList<EachCert> getCert_total() {
        return cert_total;
    }
    public void addnewItem(EachCert ec){
        cert_total.add(ec);
    }
}
