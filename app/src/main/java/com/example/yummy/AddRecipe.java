package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddRecipe extends AppCompatActivity {

    private Button addRecipe,viewRecipes;
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
    Recipeclass recipeclass;
    public String rep = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);



        recipeImageRef = FirebaseStorage.getInstance().getReference().child("Recipe Images");
        recipeRef = FirebaseDatabase.getInstance().getReference().child("Recipe");

        addRecipe = (Button) findViewById(R.id.add_recipe_btn);
        viewRecipes = findViewById(R.id.view_recipe_btn);
        inputRecipeName = (EditText) findViewById(R.id.recipeName);
        inputIngredients = (EditText) findViewById(R.id.ingredientList);
        inputMethod = (EditText) findViewById(R.id.recipe_method);
        inputPortion = (EditText) findViewById(R.id.recipe_portion);
        RecipeImage = (ImageView) findViewById(R.id.recipe_image);
        loadingBar = new ProgressDialog(this);


        try {
            rep = getIntent().getExtras().getString("rep");

            recipeclass = AlbumsAdapter.rep.get(0);
            AlbumsAdapter.rep.remove(0);
            inputRecipeName.setHint(recipeclass.getName());
            inputIngredients.setHint(recipeclass.getIngredients());
            inputMethod.setHint(recipeclass.getMethod());
            inputPortion.setHint(recipeclass.getPortion());


        }catch (Exception e){

        }

//        RecipeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OpenGallery();
//
//            }
//        });

        viewRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddRecipe.this,RecipeList.class);
                intent.putExtra("Radmin","R");
                startActivity(intent);
            }
        });

        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rep.equals("rep")) {
                    recipeclass.setName(recipeclass.getName());
                    recipeclass.setIngredients(recipeclass.getIngredients());
                    recipeclass.setMethod(recipeclass.getMethod());
                    recipeclass.setPortion(inputPortion.getText().toString());

                    DatabaseReference databaseRefere = FirebaseDatabase.getInstance().getReference("Recipe");
                    databaseRefere.child(recipeclass.getName()).setValue(recipeclass);
                } else {

                    Recipeclass recipeclass = new Recipeclass();

                    recipeclass.setName(inputRecipeName.getText().toString());
                    recipeclass.setIngredients(inputIngredients.getText().toString());
                    recipeclass.setMethod(inputMethod.getText().toString());
                    recipeclass.setPortion(inputPortion.getText().toString());

                    DatabaseReference databaseRefere = FirebaseDatabase.getInstance().getReference("Recipe");
                    databaseRefere.child(recipeclass.getName()).setValue(recipeclass);

//                ValidateRecipedata();
                }
            }
        });

    }

    private void ValidateRecipedata() {
        rname = inputRecipeName.getText().toString();
        ringredients = inputIngredients.getText().toString();
        rmethod = inputMethod.getText().toString();
        rportion = inputPortion.getText().toString();

        if(ImageUri == null){
//            Toast.makeText(this,"Recipe image required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(rname)){
            Toast.makeText(this,"Recipe name required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(ringredients)){
            Toast.makeText(this,"Ingredients required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(rmethod)){
            Toast.makeText(this,"Method required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(rportion)){
            Toast.makeText(this,"Recipe portion size required",Toast.LENGTH_SHORT).show();
        }
        else{
            StoreRecipeInfo();
        }
    }

    private void StoreRecipeInfo() {
        loadingBar.setTitle("Add new Recipe");
        loadingBar.setMessage("Please wait while we are adding new recipe");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        RecipeKey = inputRecipeName.getText().toString();


        final StorageReference filepath = recipeImageRef.child(ImageUri.getLastPathSegment() + RecipeKey + ".jpg");

        final UploadTask uploadTask = filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddRecipe.this, "Image upload failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddRecipe.this, "Image upload suceesfull", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();

                        }

                        downloadImageUrl = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        downloadImageUrl = task.getResult().toString();
                        if(task.isSuccessful()){
                            Toast.makeText(AddRecipe.this, "Image saved to database succesfully.", Toast.LENGTH_SHORT).show();

                            SaveRecipeInfotoDb();
                        }
                    }
                });
            }
        });

    }

    private void SaveRecipeInfotoDb() {
        HashMap<String,Object> recipeMap = new HashMap<>();
        recipeMap.put("image" , downloadImageUrl);
        recipeMap.put("name" , rname);
        recipeMap.put("ingredients" , ringredients);
        recipeMap.put("method" , rmethod);
        recipeMap.put("portion" , rportion);

        recipeRef.child(RecipeKey).updateChildren(recipeMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(AddRecipe.this, RecipeAddActivity.class);
                            startActivity(intent);
                            loadingBar.dismiss();
                            Toast.makeText(AddRecipe.this, "Product added succesfully.", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AddRecipe.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null){
            ImageUri=data.getData();
            RecipeImage.setImageURI(ImageUri);
        }
    }


}