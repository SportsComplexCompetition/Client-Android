package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.User;

import java.util.ArrayList;

public class ManageUser {
    private static ManageUser manageUser=null;
    User user;
    User me;
    private ArrayList<User> user_total;
    public static ManageUser getInstance(){
        if(manageUser==null){
            manageUser=new ManageUser();
        }
        return manageUser;
    }

    private ManageUser(){
        user=new User();
        me=new User();
        user_total=new ArrayList<>();
    }

    public void setUser_total(ArrayList<User> user_total) {
        this.user_total = user_total;
    }

    public static ManageUser getManageUser() {
        return manageUser;
    }

    public void setMe(User me) {
        this.me = me;
    }

    public User getMe() {
        return me;
    }
}
