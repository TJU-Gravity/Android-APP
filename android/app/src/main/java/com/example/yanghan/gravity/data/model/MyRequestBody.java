package com.example.yanghan.gravity.data.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MyRequestBody {

        public String sortedBy="";
        public Integer page=0;
        public Integer size=0;
        public String username="";
        public Integer ID;

    }


