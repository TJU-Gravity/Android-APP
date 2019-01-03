package com.example.yanghan.gravity.data.model;



import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class News {

<<<<<<< HEAD
    public int newsid=0;
    public String title="";
=======
    public int newsID=0;
    public  String title="";
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
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
