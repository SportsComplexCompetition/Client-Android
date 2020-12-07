package com.eduvation.pecontest.Class;

public class NewAttend {
    int user_pk;
    int money;

    public NewAttend(){
    }

    public NewAttend(int user_pk, int money){
        this.user_pk=user_pk;
        this.money=money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getUser_pk() {
        return user_pk;
    }

    public void setUser_pk(int user_pk) {
        this.user_pk = user_pk;
    }
}
