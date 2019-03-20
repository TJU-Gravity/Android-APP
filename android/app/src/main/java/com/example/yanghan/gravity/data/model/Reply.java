package com.example.yanghan.gravity.data.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Reply {
    public int replyid;

    public int postid;

    public String posterid="";
    public String posterName="";

    public Date postingtime=new Date();

    public String replybody="";

}
