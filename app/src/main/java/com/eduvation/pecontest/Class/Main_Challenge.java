package com.eduvation.pecontest.Class;

public class Main_Challenge {
    String cat;
    String period;
    int people;
    int img;

    public Main_Challenge(){}

    public Main_Challenge(String cat, String period, int people, int img){
        this.cat=cat;
        this.period=period;
        this.people=people;
        this.img=img;
    }
    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
