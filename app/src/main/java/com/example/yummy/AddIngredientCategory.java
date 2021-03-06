package com.example.yummy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddIngredientCategory extends AppCompatActivity {

    private ImageView dairy, veg, fruits;
    private ImageView desserts, spices, meats;
    private ImageView oils, nuts, seafood;
    private ImageView beverages, soups, grains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient_category);

        dairy = (ImageView) findViewById(R.id.dairy_img);
        veg = (ImageView) findViewById(R.id.vegetables_img);
        fruits = (ImageView) findViewById(R.id.fruits_img);

        desserts = (ImageView) findViewById(R.id.choco_img);
        spices = (ImageView) findViewById(R.id.spices_img);
        meats = (ImageView) findViewById(R.id.meats_img);

        oils = (ImageView) findViewById(R.id.dairy_oils);
        nuts = (ImageView) findViewById(R.id.nuts_img);
        seafood = (ImageView) findViewById(R.id.seafood_img);

        beverages = (ImageView) findViewById(R.id.beverage_img);
        soups = (ImageView) findViewById(R.id.soup_img);
        grains = (ImageView) findViewById(R.id.grains_img);

        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "Added Sweeteners");
                intent.putExtra("countofing", 0);
                startActivity(intent);
            }
        });

        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "vegetables");
                intent.putExtra("countofing", 1);
                startActivity(intent);
            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "fruits");
                intent.putExtra("countofing", 2);
                startActivity(intent);
            }
        });

        desserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "desserts");
                intent.putExtra("countofing", 3);
                startActivity(intent);
            }
        });

        spices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "spices");
                intent.putExtra("countofing", 4);
                startActivity(intent);
            }
        });

        meats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "meats");
                startActivity(intent);
            }
        });

        oils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "oils");
                startActivity(intent);
            }
        });

        nuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "nuts");
                startActivity(intent);
            }
        });

        seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "seafood");
                startActivity(intent);
            }
        });

        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "beverages");
                startActivity(intent);
            }
        });

        soups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "soups");
                startActivity(intent);
            }
        });

        grains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddIngredientCategory.this, AddAdmin.class);
                intent.putExtra("category", "grains");
                startActivity(intent);
            }
        });
    }
}