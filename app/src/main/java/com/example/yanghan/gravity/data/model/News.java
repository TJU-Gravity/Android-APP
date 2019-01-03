package com.example.yanghan.gravity.data.model;



import java.util.ArrayList;
import java.util.Date;

public class News {

    public int newsid=0;
    public  String title="";
    public String newsbody="";
    public String editorid="";
    public String sponsor="";
    public Date registrationstartdate=new Date();
    public Date registrationenddate=new Date();
    public Date conteststartdate=new Date();
    public Date contestenddate=new Date();
    public String poster="";
    public int hits=0;
    public ArrayList<String> tags=new ArrayList<>();

}
