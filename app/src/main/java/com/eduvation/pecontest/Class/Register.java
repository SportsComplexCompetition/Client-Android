package com.eduvation.pecontest.Class;

public class Register {
    String email;
    String password1;
    String password2;
    String nickname;
    String age;
    int location;

    public Register(){

    }
    public Register(String email, String password1, String password2, String nickname, String age, int location) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.nickname = nickname;
        this.age = age;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
