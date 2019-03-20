package com.example.yanghan.gravity.data.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReplyResult extends Reply {
    public String headshot="";
    public String nickname="";
}
