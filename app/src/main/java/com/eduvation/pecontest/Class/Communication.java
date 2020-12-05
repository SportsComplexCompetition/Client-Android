package com.eduvation.pecontest.Class;

import java.util.Date;

public class Communication {
    String host_email;
    String host_key;
    String location;
    String title;
    int people;
    String body;
    Date date;
    public Communication(){}
    public Communication(String host_email, String host_key, String location, String title, int people, String body, Date date){
        this.host_email=host_email;
        this.host_key=host_key;
        this.location=location;
        this.title=title;
        this.people=people;
        this.body=body;
        this.date=date;
    }

    public String getHost_email() {
        return host_email;
    }

    public void setHost_email(String host_email) {
        this.host_email = host_email;
    }

    public String getHost_key() {
        return host_key;
    }

    public void setHost_key(String host_key) {
        this.host_key = host_key;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
