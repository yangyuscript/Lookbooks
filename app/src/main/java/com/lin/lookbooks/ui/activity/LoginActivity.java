package com.lin.lookbooks.ui.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lin.lookbooks.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void goMain(View view){
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void goRegist(View view){
        Log.e("haha","wocao");
        Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
        startActivity(intent);

    }
}

