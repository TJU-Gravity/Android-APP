package com.example.yanghan.gravity.data.model;

import java.util.Date;

public class Post {
    public int postId=0;
    public char posttype=0;
    public String posterID="";
    public String posterUsername="";
    public Date postingTime=new Date();
    public String title="";
    public String postBody="";
    public String description="";
    public int hits=0;
    public Date lastPost=new Date();
}
