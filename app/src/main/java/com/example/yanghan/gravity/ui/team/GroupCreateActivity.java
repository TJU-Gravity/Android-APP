package com.example.yanghan.gravity.ui.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanghan.gravity.R;
import com.example.yanghan.gravity.data.model.Team;
import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.RequestManeger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.json.JSONObject;

import java.io.IOException;

public class GroupCreateActivity extends AppCompatActivity {
    private String TeamName;
    private String TeamMatch;
    private String TeamMember;
    private String TeamProfile;
    private ExtendedEditText name;
    private ExtendedEditText profile;
    private ExtendedEditText member;
    private Button button;
    private Drawer result = null;




    class TeamCreateResponse {
        @JsonProperty(value = "code")
        String code = "";
        @JsonProperty(value = "data")
        String data="";
        @JsonProperty(value = "message")
        String message = "";
    }


    public class TeamCreateCallBack implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("request", e.toString());

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try{
                JSONObject object = new JSONObject(response.body().string());
                String result= object.getString("code");
                Log.e("code: ", result);
                if("200".equals(result)){

                    Intent intent=new Intent(GroupCreateActivity.this,TeamActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.e("aiya", "这里有问题");
                }
            }catch (Exception e)
            {
                Log.e("error",e.getMessage());
            }




        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);

        final User user=new User();
        user.loadUser(this);


        Toolbar group_toolbar = (Toolbar) findViewById(R.id.group_toolbar);
        setSupportActionBar(group_toolbar);
        result = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withFullscreen(true)
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        init();
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //这里只进行了非空的判断
                if(name.getText().toString().trim().isEmpty()||profile.getText().toString().trim().isEmpty()||member.getText().toString().trim().isEmpty()){
                    Toast.makeText(GroupCreateActivity.this, "信息不能为空！", Toast.LENGTH_SHORT).show();

                }
                else{
                    Team team=new Team();
                    team.teamname=name.getText().toString();
                    team.introduction=profile.getText().toString();
                    //team.TeamMumber.add(member.getText().toString());
                    team.captainid=user.username;
                    team.memberNum=1;
                    ObjectMapper mapper = new ObjectMapper();
                    String json="";
                    try
                    {
                        json=mapper.writeValueAsString(team);
                        Log.e("login",json);
                    }
                    catch (Exception e)
                    {
                        // Log.e("login","json");
                    }

                    RequestManeger requestManeger=new RequestManeger();
                    String response="";
                    TeamCreateCallBack callback=new TeamCreateCallBack();
                    requestManeger.post("http://118.25.41.237:8080/team/createTeam",json,callback);

                }
            }
        });
    }

    private void init(){



        name=(ExtendedEditText )findViewById(R.id.edit_note);
        profile=(ExtendedEditText)findViewById(R.id.profile_note);
        member=(ExtendedEditText)findViewById(R.id.member_note);

        button=(Button)findViewById(R.id.button);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_grouptoolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
       // this.finish();
    }
}
