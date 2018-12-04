package com.example.yanghan.gravity.data.model;



import java.util.ArrayList;
import java.util.Date;

public class News {
    public int newsID=0;
    public  String title="";
    public String newsbody="";
    public String editorID="";
    public String sponsor="";
    public Date registrationStartDate=new Date();
    public Date registrationEndDate=new Date();
    public Date contestStartDate=new Date();
    public Date contestEndDate=new Date();
    public String poster="";
    public int hits=0;
    public ArrayList<String> tags=new ArrayList<>();

}
