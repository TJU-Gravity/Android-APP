package com.example.yanghan.gravity.data.model;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Result {

    public int code;
    public Page data=new Page();
    public String message;

    public static Result mapper(String json)
    {
        Result result=new Result();
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            result=mapper.readValue(json,Result.class);


        }
        catch (Exception e)
        {
            Log.e("mapper: ", e.toString());
        }

        return result;

    }
}

