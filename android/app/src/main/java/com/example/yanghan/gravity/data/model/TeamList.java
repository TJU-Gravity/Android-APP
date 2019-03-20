package com.example.yanghan.gravity.data.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TeamList {
        public int teamID=0;
        public String teamName="";
        public String headshot="";

}
