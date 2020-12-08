package com.eduvation.pecontest.Class;

import java.util.ArrayList;
import java.util.Date;

public class Competition {
    String host_nickname;
    int location;
    int id;
    int comp_type;
    String category;
    String title;
    Date created_at;
    String ended_at;
    int max_people;
    int require_money;
    int total_money;
    int host;
    String winner;
    ArrayList<Integer> joined_people;

    public Competition(){
    }


    public Competition(String host_nickname, int location, int id, int comp_type, String category, String title, Date created_at, String ended_at, int max_people, int require_money, int total_money, int host, String winner, ArrayList<Integer> joined_people){
        this.host_nickname=host_nickname;
        this.location=location;
        this.id=id;
        this.comp_type=comp_type;
        this.category=category;
        this.title=title;
        this.created_at=created_at;
        this.ended_at=ended_at;
        this.max_people=max_people;
        this.require_money=require_money;
        this.total_money=total_money;
        this.host=host;
        this.winner=winner;
        this.joined_people=joined_people;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComp_type() {
        return comp_type;
    }

    public void setComp_type(int comp_type) {
        this.comp_type = comp_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(String ended_at) {
        this.ended_at = ended_at;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public String getHost_nickname() {
        return host_nickname;
    }

    public void setHost_nickname(String host_nickname) {
        this.host_nickname = host_nickname;
    }

    public int getRequire_money() {
        return require_money;
    }

    public void setRequire_money(int require_money) {
        this.require_money = require_money;
    }

    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public ArrayList<Integer> getJoined_people() {
        return joined_people;
    }

    public void setJoined_people(ArrayList<Integer> joined_people) {
        this.joined_people = joined_people;
    }
}
