package com.eduvation.pecontest.Class;

import android.net.Uri;

public class EachCert {
    int pk;
    String name;
    int count;
    Uri data=null;
    int type;
    public EachCert(){}
    public EachCert(int pk, String name, int count, Uri data, int type){
        this.pk=pk;
        this.name=name;
        this.count=count;
        this.data=data;
        this.type=type;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
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
