package com.example.yummy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class AddRecipe extends AppCompatActivity {

    private Button addRecipe;
    private EditText inputRecipeName, inputIngredients, inputMethod, inputPortion;
    private ImageView RecipeImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String rname, ringredients, rmethod, rportion, saveCurrentDate, saveCurrentTime;
    private String RecipeKey;
    private StorageReference recipeImageRef;
    private DatabaseReference recipeRef;
    private String downloadImageUrl;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }



}