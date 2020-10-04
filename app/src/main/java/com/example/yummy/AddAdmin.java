package com.example.yummy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class AddAdmin extends AppCompatActivity {

    private String categoryName, ingredientName;
    private int countofing;
    private Button addIngredientBtn;
    private EditText inputIngredient;
    private String saveCurrentDate, saveCurrentTime;
    private String ingredientKey;
    private DatabaseReference ingredientRef;
    private ProgressDialog loadingBar;
    private AddminIngAdapter adapter;
    ViewPager viewPager;


    ArrayList<String> ingredients = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);


        categoryName = getIntent().getExtras().get("category").toString();
//        categoryName = getIntent().getExtras().get("category").toString();
        countofing = (int) getIntent().getExtras().get("countofing");

        viewPager = findViewById(R.id.view11);
        PlansPagerAdapter adapter = new PlansPagerAdapter(getSupportFragmentManager(),1,1,countofing);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(1);


        Log.d("lg", String.valueOf(ingredients));

        ingredientRef = FirebaseDatabase.getInstance().getReference().child("IngredientsCategory").child(categoryName);

        addIngredientBtn = (Button) findViewById(R.id.add_ingredient_btn);
        inputIngredient = (EditText) findViewById(R.id.ingredientName);
        loadingBar = new ProgressDialog(this);

//        inputIngredient.setHint(namex);


        addIngredientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateIngredient();
            }
        });

    }


    private void ValidateIngredient(){
        ingredientName = inputIngredient.getText().toString();

        if(TextUtils.isEmpty(ingredientName)){
            Toast.makeText(this,"Please insert new ingredient.",Toast.LENGTH_SHORT).show();
        }else{
            StoreIngredient();
        }
    }
    private void StoreIngredient() {
        loadingBar.setTitle("Adding new Ingredient");
        loadingBar.setMessage("Please wait while we are adding new ingredient");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        ingredientKey = saveCurrentDate + saveCurrentTime;
        SaveIngredient();

    }

    private void SaveIngredient() {
        final ArrayList<String> ing = new ArrayList<>();
        ingredientRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ing.add((String) dataSnapshot.getValue());
                }
                ing.add(ingredientName);
//                ingredientRef.child(categoryName).setValue(ing)
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("IngredientsCategory").child(categoryName);
                databaseReference.setValue(ing)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(AddAdmin.this, AddIngredientCategory.class);
                                    startActivity(intent);

                                    loadingBar.dismiss();
                                    Toast.makeText(AddAdmin.this, "Ingredient added succesfully", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    loadingBar.dismiss();
                                    String message = task.getException().toString();
                                    Toast.makeText(AddAdmin.this,"Error: "+message,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        ingredientRef.child(categoryName).updateChildren(ingredientAdd)

    }

}