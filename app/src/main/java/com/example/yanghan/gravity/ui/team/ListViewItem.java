package com.example.yanghan.gravity.ui.team;

/**
 * 列表相对象
 * Created by 程果 on 2016/3/6.
 */
public class ListViewItem {
    //用户头像
    private int userImg;
    //用户名字
    private String userName;
    //用户的评论
    private String userComment;

    public int getUserImg() {
        return userImg;
    }

    public void setUserImg(int userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}
