package com.eduvation.pecontest.Class;

public class Area_Rank_Data {
    String location;
    String gold;
    String silver;
    String bronze;
    String match;

    public Area_Rank_Data(){}
    public Area_Rank_Data(String location, String gold, String silver, String bronze, String match){
        this.location=location;
        this.gold=gold;
        this.silver=silver;
        this.bronze=bronze;
        this.match=match;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getSilver() {
        return silver;
    }

    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getBronze() {
        return bronze;
    }

    public void setBronze(String bronze) {
        this.bronze = bronze;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }
}
