package com.example.yummy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yummy.Model.Onlineuser;
import com.example.yummy.Model.User;
import com.example.yummy.Prevelent.Prevelen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    EditText profilename;
    TextView username,settings;
    Button editname,removeacc,fav,home,conversion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        conversion = findViewById(R.id.profilecon);
        profilename = findViewById(R.id.profileeditname);
        settings = findViewById(R.id.profilesettings);
        editname = findViewById(R.id.change);
        removeacc = findViewById(R.id.removeacc);

        username = (TextView) findViewById(R.id.user_profile_name);
        username.setText(Onlineuser.onlineuser.getName());

        fav = findViewById(R.id.profilefav);
        home = findViewById(R.id.profilehome);

        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Conversion.class);
                startActivity(intent);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilename.setVisibility(View.VISIBLE);
                editname.setVisibility(View.VISIBLE);
            }
        });

        removeacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(Onlineuser.onlineuser.getPhone());
                databaseReference.removeValue();
                Intent intent = new Intent(Home.this,MainPage.class);
                startActivity(intent);
            }
        });

        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(profilename.getText().toString());
                user.setPassword(Onlineuser.onlineuser.getPassword());
                user.setPhone(Onlineuser.onlineuser.getPhone());

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(Onlineuser.onlineuser.getPhone()).setValue(user);
            }
        });

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