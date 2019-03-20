package com.example.yanghan.gravity.data.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponseResult {
    public int code;
    public Object data;
    public String message;
}
