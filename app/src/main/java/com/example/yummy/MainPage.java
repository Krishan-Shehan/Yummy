package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yummy.Model.User;
import com.example.yummy.Prevelent.Prevelen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainPage extends AppCompatActivity {
    private Button joinnowbtn,loginbtn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        joinnowbtn = (Button) findViewById(R.id.main_join_now_btn);
        loginbtn = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, Login.class);
                startActivity(intent);
            }
        });

        joinnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, Register.class);
                startActivity(intent);
            }
        });


//        String UserPhoneKey = Paper.book().read(Prevelen.userPhoneKey);
//        String UserPasswordKey = Paper.book().read(Prevelen.userPasswordKey);

//        if (UserPhoneKey != "" && UserPasswordKey != "") {
//            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {
//                AllowAccess(UserPhoneKey, UserPasswordKey);
//
//                loadingBar.setTitle("Already Logged in");
//                loadingBar.setMessage("Please wait...");
//                loadingBar.setCanceledOnTouchOutside(false);
//                loadingBar.show();
//            }
//        }
    }

        private void AllowAccess(final String phone, final String password) {
            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child("Users").child(phone).exists()){
                        User userData = snapshot.child("Users").child(phone).getValue(User.class);

                        if(userData.getPhone().equals(phone)){
                            if(userData.getPassword().equals(password)){
                                Toast.makeText(MainPage.this,"You are already logged in.",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(MainPage.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                loadingBar.dismiss();
                                Toast.makeText(MainPage.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(MainPage.this,"Account with "+phone+ " number do not exist.Please create a new account",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}