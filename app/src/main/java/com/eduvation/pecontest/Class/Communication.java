package com.eduvation.pecontest.Class;

public class Communication {
    String location;
    String name;
    int img;

    public Communication(){}

    public Communication(String location, String name, int img){
        this.location=location;
        this.name=name;
        this.img=img;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
