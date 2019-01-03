package com.example.yanghan.gravity.data.model;

import android.util.JsonToken;
import android.util.Log;

import com.example.yanghan.gravity.data.other.LoginManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Result {
    public String code;
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

