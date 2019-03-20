package com.example.yanghan.gravity.data.model;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PostDetail {
    public PostResult post=new PostResult();
    public Team team=new Team();
    public User user=new User();
    public ArrayList<ReplyResult> replies=new ArrayList<>();
}
