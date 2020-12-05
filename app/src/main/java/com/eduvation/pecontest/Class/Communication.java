package com.eduvation.pecontest.Class;

import java.util.Date;

public class Communication {
    String host_email;
    String host;
    int location;
    String title;
    int find_people;
    String body;
    Date created_at;
    public Communication(){}
    public Communication(String host_email, String host, int location, String title, int find_people, String body, Date created_at){
        this.host_email=host_email;
        this.host=host;
        this.location=location;
        this.title=title;
        this.find_people=find_people;
        this.body=body;
        this.created_at=created_at;
    }

    public String getHost_email() {
        return host_email;
    }

    public void setHost_email(String host_email) {
        this.host_email = host_email;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
