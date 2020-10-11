package com.example.yummy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummy.Model.Onlineuser;
import com.example.yummy.Model.User;
import com.example.yummy.Prevelent.Prevelen;

public class Home extends AppCompatActivity {

    TextView username;
    Button editname,removeacc,fav,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (TextView) findViewById(R.id.user_profile_name);
        username.setText(Onlineuser.onlineuser.getName());

        fav = findViewById(R.id.profilefav);
        home = findViewById(R.id.profilehome);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Favorites.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}