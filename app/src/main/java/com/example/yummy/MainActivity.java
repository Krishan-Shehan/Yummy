package com.example.yummy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yummy.Model.Onlineuser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button,fav;

    ViewPager viewPager;
    Adapter adapter;
    List<Swaping> swapings;
    //Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("checkUser", Onlineuser.onlineuser.getPhone());

        fav = findViewById(R.id.button);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,IngredientList.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button22);
        button.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,RecipeList.class);
               startActivity(intent);
            }
        });


        swapings = new ArrayList<>();
        swapings.add(new Swaping(R.drawable.egg_cress_club_sandwich_0, "Recipe1"));
        swapings.add(new Swaping(R.drawable.image__2, "Recipe2"));
        swapings.add(new Swaping(R.drawable.image__5, "Recipe3"));
        swapings.add(new Swaping(R.drawable.image__6, "Recipe4"));

        adapter = new Adapter(swapings, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);


        //colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}