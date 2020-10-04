package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IngredientList extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    DatabaseReference databaseReference;
    public List<String> ingredients = new ArrayList<>();
    private Button btnnext;

    int ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
        btnnext = (Button) findViewById(R.id.find);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IngredientList.this,RecipeList.class);
                startActivity(intent);
            }
        });
//        test();


        databaseReference = FirebaseDatabase.getInstance().getReference("IngredientsCategory");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String ing = (String) dataSnapshot.getKey();
                    ingredients.add(ing);
                }
                Log.d("kk", String.valueOf(ingredients));
                ct = ingredients.size();
                Log.d("kkk", ct+"");

                tab = findViewById(R.id.tabs);
                viewPager = findViewById(R.id.frameLayout);

                for (int k = 0; k <ct; k++) {
                    tab.addTab(tab.newTab().setText(ingredients.get(k)));
                }

                PlansPagerAdapter adapter = new PlansPagerAdapter(getSupportFragmentManager(),tab.getTabCount(),0,9);
                viewPager.setAdapter(adapter);
                viewPager.setOffscreenPageLimit(1);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

                if (tab.getTabCount() == 2) {
                    tab.setTabMode(TabLayout.MODE_FIXED);
                } else {
                    tab.setTabMode(TabLayout.MODE_SCROLLABLE);
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        Log.d("kks", String.valueOf(ingredients));


    }


}