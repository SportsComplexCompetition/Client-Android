package com.eduvation.pecontest.Class;

import java.util.ArrayList;
import java.util.Date;

public class Competition {
    int id;
    int comp_type;
    String category;
    String title;
    Date created_at;
    Date ended_at;
    int max_people;
    int money;
    int host;
    String winner;
    ArrayList<Integer> joined_people;

    public Competition(){}

    public Competition(int id, int money){
        this.id=id;
        this.money=money;
    }
    public Competition(int id, int comp_type, String category, String title, Date created_at, Date ended_at, int max_people, int money, int host, String winner, ArrayList<Integer> joined_people){
        this.id=id;
        this.comp_type=comp_type;
        this.category=category;
        this.title=title;
        this.created_at=created_at;
        this.ended_at=ended_at;
        this.max_people=max_people;
        this.money=money;
        this.host=host;
        this.winner=winner;
        this.joined_people=joined_people;
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

    public Date getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(Date ended_at) {
        this.ended_at = ended_at;
    }

    public int getMax_people() {
        return max_people;
    }

    public void setMax_people(int max_people) {
        this.max_people = max_people;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
