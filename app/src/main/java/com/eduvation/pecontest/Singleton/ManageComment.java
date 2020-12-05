package com.eduvation.pecontest.Singleton;

import com.eduvation.pecontest.Class.Comment;

import java.util.ArrayList;

public class ManageComment {
    private static ManageComment manageComment=null;
    Comment comment;
    private ArrayList<Comment> comment_total;
    public static ManageComment getInstance(){
        if(manageComment==null){
            manageComment=new ManageComment();
        }
        return manageComment;
    }

    private ManageComment(){
        comment=new Comment();
        comment_total=new ArrayList<>();
    }

    public void setComment_total(ArrayList<Comment> comment_total) {
        this.comment_total = comment_total;
    }

    public ArrayList<Comment> getComment_total() {
        return comment_total;
    }

    public void addnewComment(Comment comment){
        comment_total.add(comment);
    }
}
