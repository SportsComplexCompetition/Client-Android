package com.eduvation.pecontest.Class;

import java.util.Date;

public class Communication {
    int pk;
    String host_nickname;
    int host;
    int location;
    String title;
    int find_people;
    String body;
    Date created_at;
    String category;
    String address;

    public Communication(){}

    public Communication(int pk, String host_nickname, int host, int location, String title, int find_people, String body, Date created_at, String category, String address){
        this.pk=pk;
        this.host_nickname=host_nickname;
        this.host=host;
        this.location=location;
        this.title=title;
        this.find_people=find_people;
        this.body=body;
        this.created_at=created_at;
        this.category=category;
        this.address=address;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getHost_nickname() {
        return host_nickname;
    }

    public void setHost_nickname(String host_nickname) {
        this.host_nickname = host_nickname;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFind_people() {
        return find_people;
    }

    public void setFind_people(int find_people) {
        this.find_people = find_people;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
