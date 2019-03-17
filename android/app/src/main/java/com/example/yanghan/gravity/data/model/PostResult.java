package com.example.yanghan.gravity.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PostResult extends Post
{

    @JsonProperty("nickname")
    public String posterNickname="";

    public String headshot;


}
