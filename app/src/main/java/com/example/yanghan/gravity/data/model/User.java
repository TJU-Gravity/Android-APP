package com.example.yanghan.gravity.data.model;



import java.util.ArrayList;

public class User
{
    public String username="";
    public String nickname="";
    public String password="";
    public String gender="";

    public String headshot="";
    public String location="";
    public String phoneNumber="";
    public String introduction="";
    public String email="";
    public ArrayList<String> tags=new ArrayList<>();;

    public User()
    {

        tags.add("标签一");
        tags.add("标签二");
        tags.add("标签三");
        tags.add("标签四");
        tags.add("标签五");
    }



}
