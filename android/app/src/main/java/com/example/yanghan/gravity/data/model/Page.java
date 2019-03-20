package com.example.yanghan.gravity.data.model;

import android.util.JsonToken;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Page {
    public int endRow;
    public int firstPage;
    public boolean hasNextPage;
    public boolean hasPreviousPage;
    public boolean isFirstPage;
    public boolean isLastPage;
    public int lastPage;
    public int navigateFirstPage;
    public int navigateLastPage;
    public int navigatePages;
    public ArrayList navigatepageNums;
    public int nextPage;
    public String orderBy;
    public int pageNum;
    public int pageSize;
    public int pages;
    public int prePage;
    public int size;
    public int startRow;
    public int total;
    public ArrayList list;




}
