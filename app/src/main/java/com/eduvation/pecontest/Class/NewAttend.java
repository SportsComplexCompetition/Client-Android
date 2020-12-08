package com.eduvation.pecontest.Class;

public class NewAttend {
    int user_pk;
    int total_money;

    public NewAttend(){
    }

    public NewAttend(int user_pk, int total_money){
        this.user_pk=user_pk;
        this.total_money=total_money;
    }

    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }

    public int getUser_pk() {
        return user_pk;
    }

    public void setUser_pk(int user_pk) {
        this.user_pk = user_pk;
    }
}
