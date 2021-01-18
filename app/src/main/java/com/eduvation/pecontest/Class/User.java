package com.eduvation.pecontest.Class;

public class User {
    int pk;
    String email;
    String nickname;
    int location;
    int age;
    String sex;
    public User(){}

    public User(int pk, String email, String nickname, int location, int age, String sex){
        this.pk=pk;
        this.email=email;
        this.nickname=nickname;
        this.location=location;
        this.age=age;
        this.sex=sex;
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
