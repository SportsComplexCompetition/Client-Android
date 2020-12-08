package com.eduvation.pecontest.Class;

import android.net.Uri;

public class Cert {
    String name;
    int count;
    Uri data=null;
    int type;
    public Cert(){}
    public Cert(String name, int count, Uri data, int type){
        this.name=name;
        this.count=count;
        this.data=data;
        this.type=type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Uri getData() {
        return data;
    }

    public void setData(Uri data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
