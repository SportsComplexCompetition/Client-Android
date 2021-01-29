package com.eduvation.pecontest.Class;

import java.util.Date;

public class MyCompetition {
    int id;
    int host_id;
    int comp_type;
    int location;
    String category;
    String title;
    Date created_at;
    String ended_at;
    int max_people;
    int require_money;
    int total_money;
    String winner_id;

    public MyCompetition(){}

    public MyCompetition(int id, int host_id, int comp_type, int location, String category, String title, Date created_at, String ended_at, int max_people, int require_money, int total_money, String winner_id) {
        this.id = id;
        this.host_id = host_id;
        this.comp_type = comp_type;
        this.location = location;
        this.category = category;
        this.title = title;
        this.created_at = created_at;
        this.ended_at = ended_at;
        this.max_people = max_people;
        this.require_money = require_money;
        this.total_money = total_money;
        this.winner_id = winner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHost_id() {
        return host_id;
    }

    public void setHost_id(int host_id) {
        this.host_id = host_id;
    }

    public int getComp_type() {
        return comp_type;
    }

    public void setComp_type(int comp_type) {
        this.comp_type = comp_type;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
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

    public String getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(String winner_id) {
        this.winner_id = winner_id;
    }
}
