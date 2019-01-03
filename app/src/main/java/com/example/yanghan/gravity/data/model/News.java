package com.example.yanghan.gravity.data.model;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class News {

    public int newsid=0;
    public String title="";
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

    public News()
    {
        tags.add("标签一");
        tags.add("标签二");
        tags.add("标签三");
        tags.add("标签四");
        tags.add("标签五");
    }


}
