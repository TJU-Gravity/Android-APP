package com.example.yanghan.gravity.data.model;



import java.util.ArrayList;
import java.util.Date;

public class Team {

    public int teamID=0;
    public String teamName="";
    public String captainID="";
    public int memberNum=0;
    public Date createDate=new Date();

    public String headshot="";
    public String introduction="";
    public ArrayList<String> tags=new ArrayList<>();

}
