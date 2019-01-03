package com.example.yanghan.gravity.ui.login;

<<<<<<< HEAD
import android.app.Activity;
import android.content.Context;
=======
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
import android.util.Log;
import android.view.View;

import com.example.yanghan.gravity.data.model.User;
import com.example.yanghan.gravity.data.other.LoginManager;

<<<<<<< HEAD
import androidx.lifecycle.ViewModel;


=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
public class LoginViewModel extends ViewModel {
    public User user=new User();
    private LoginManager loginManager=new LoginManager();
    boolean isValiad=false;
<<<<<<< HEAD
    Context context;
    LoginViewModel(Context context)
    {
        this.context=context;
    }
=======
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c


    public void login(View v)
    {
        Log.e("click","login");
<<<<<<< HEAD

        Log.e("username",user.username);
        Activity a=(LoginActivity) v.getContext();
        if(isValiad)
        {
            loginManager.login(v.getContext(),user,this);
        }
        //不合法处理

    }
    public void loginFailed()
    {
        ((LoginActivity)context).loginFailed();
    }
    public void loginSucceed()
    {
        ((LoginActivity)context).loginSucceed();
    }
    public void error()
    {
        ((LoginActivity)context).error();
    }
=======
        Log.e("username",user.username);
        if(isValiad)
            loginManager.login(v.getContext(),user);
        //不合法处理

    }
>>>>>>> 23c7d3b3ec69dcf6812b50acae85e4f21969dd7c
}
