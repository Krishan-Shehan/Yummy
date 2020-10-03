package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummy.Model.User;
import com.example.yummy.Prevelent.Prevelen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {

    private EditText inputNumber, inputPassword;
    private Button loginbtn;
    private ProgressDialog loadingBar;
    private TextView adminLink, notAdminLink;

    private String parentDbname = "Users";
    private CheckBox chkboxRememberMe;


//    public static List<Album> rep = new ArrayList<>();

    public static List<User> loged = new ArrayList<>();

    User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = (Button) findViewById(R.id.login_btn);
        inputNumber = (EditText) findViewById(R.id.phone_input);
        inputPassword = (EditText) findViewById(R.id.password_input);
        adminLink = (TextView) findViewById(R.id.admin_panel_link);
        notAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);

        chkboxRememberMe = (CheckBox) findViewById(R.id.remember_me);
        Paper.init(this);

        users = new User();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUser();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginbtn.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDbname = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginbtn.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDbname = "Users";

            }
        });
    }

    private void LoginUser() {
        String phone = inputNumber.getText().toString();
        String password = inputPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter phone", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait while we are checking your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone,password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if(chkboxRememberMe.isChecked()){
            Paper.book().write(Prevelen.userPhoneKey, phone);

            Paper.book().write(Prevelen.userPasswordKey, password);
        }

        Log.d("ph",phone);


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(parentDbname).child(phone).exists()){
                    User userData = snapshot.child(parentDbname).child(phone).getValue(User.class);
                    if(userData.getPhone().equals(phone)){
                        if(userData.getPassword().equals(password)){
                            if(parentDbname.equals("Admins")){
                                Toast.makeText(Login.this,"Admin logged in Successfully..",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();


                                Intent intent = new Intent(Login.this,AddIngredientCategory.class);
                                startActivity(intent);
                            }
                            else if(parentDbname.equals("Users")){
                                Toast.makeText(Login.this,"Logged in Successfully..",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                loged.add(userData);
                                Intent intent = new Intent(Login.this, Home.class);
                                Prevelen.currentOnlineUser = userData;
                                startActivity(intent);
                            }
                        }
                        else{
                            loadingBar.dismiss();
                            Toast.makeText(Login.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(Login.this,"Account with "+phone+ " number do not exist.Please create a new account",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}