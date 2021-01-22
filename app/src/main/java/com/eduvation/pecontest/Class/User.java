package com.eduvation.pecontest.Class;

public class User {
    String sex;
    String key;
    int pk;
    String email;
    String nickname;
    int location;
    int age;
    public User(){}

    public User(String key, int pk, String email) {
        this.key = key;
        this.pk = pk;
        this.email = email;
    }

    public User(String key, int pk, String email, String nickname, int location, int age) {
        this.key = key;
        this.pk = pk;
        this.email = email;
        this.nickname = nickname;
        this.location = location;
        this.age = age;
    }

    public User(int pk, String email, String nickname, int location) {
        this.pk = pk;
        this.email = email;
        this.nickname = nickname;
        this.location = location;
    }

    public User(int pk, String email, String nickname, int location, int age){

        this.pk=pk;
        this.email=email;
        this.nickname=nickname;
        this.location=location;
        this.age=age;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
