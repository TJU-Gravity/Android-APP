package com.example.yanghan.gravity.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PostResult extends Post
{

    @JsonProperty("nickname")
    public String posterNickname="";

    public String headshot;


}
