package com.example.yanghan.gravity.data.model;



import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Team {

    public int teamid=0;
    public String teamname="";
    public String captainid="";

    public int membernum;
   // public String AssociateMatch;
    public Date createdate= new Date();
    public ArrayList<String> TeamMumber=new ArrayList<>();
    public String headshot="";
    public String introduction="";
   // public ArrayList<String> tags=new ArrayList<>();


}
