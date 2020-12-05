package com.eduvation.pecontest.Class;

import java.util.Date;

public class Comment {
    String user_nickname;
    int user;
    int meeting;
    String content;
    Date created_at;

    public Comment(){}

    public Comment(String user_nickname, int user, int meeting, String content, Date created_at){
        this.user_nickname=user_nickname;
        this.user=user;
        this.meeting=meeting;
        this.content=content;
        this.created_at=created_at;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getMeeting() {
        return meeting;
    }

    public void setMeeting(int meeting) {
        this.meeting = meeting;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
