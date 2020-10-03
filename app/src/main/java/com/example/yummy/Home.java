package com.example.yummy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummy.Prevelent.Prevelen;

public class Home extends AppCompatActivity {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (TextView) findViewById(R.id.user_profile_name);

        username.setText(Prevelen.currentOnlineUser.getName());
    }
}