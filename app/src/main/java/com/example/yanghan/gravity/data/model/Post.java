package com.example.yanghan.gravity.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Post {
    @JsonProperty("postid")
    public int postId=0;

    @JsonProperty("posttype")
    public char postType=0;

    @JsonProperty("posterid")
    public String posterID="";



    @JsonProperty("postingtime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date postingTime=new Date();

    public String title="";

    @JsonProperty("postbody")
    public String postBody="";

    public String description="";
    public int hits=0;

    @JsonProperty("lastpost")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date lastPost=new Date();

    @JsonProperty("teamid")
    public int teamID=0;

    public void genDescription()
    {
        if(description==null||description.equals(""))
        {
            description=postBody.substring(0,30<postBody.length()?30:postBody.length());
        }
    }
}
