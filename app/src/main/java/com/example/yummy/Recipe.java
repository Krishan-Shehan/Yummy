package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Recipe extends AppCompatActivity {

    private RecipeAdapter adapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;

    TextView favicon,addnote,Mname;
    EditText notes;
    Button addtonote;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        album = AlbumsAdapter.rep.get(0);
        AlbumsAdapter.rep.remove(0);

        Log.d("albumre", String.valueOf(album));

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.recipeview);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeAdapter(this,album);
        recyclerView.setAdapter(adapter);

        favicon = findViewById(R.id.addtofav);
        addnote = findViewById(R.id.addnote);
        notes = findViewById(R.id.notes);
        addtonote = findViewById(R.id.addtonote);
        Mname = findViewById(R.id.Mname);

        Mname.setText(album.getName());

        favicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                favicon.setCompoundDrawables(Drawable.createFromPath("@drawable/ic_baseline_add_circle_outline_24"),null,null,null);
//                Drawable[] x = favicon.getCompoundDrawables();
//                Log.d("draw", String.valueOf(x));
//                Drawable.createFromPath("@drawable/ic_baseline_add_circle_outline_24");
                Log.d("t1","click fav");
                DatabaseReference def = FirebaseDatabase.getInstance().getReference("User").child("User1").child("fav");
                try{

                    def.child(album.getName()).setValue(album);
//                    Log.d("ll", String.valueOf(album));

                }catch (Exception e){

                }
                addnote.setVisibility(View.VISIBLE);
                addnote.setClickable(true);
                favicon.setVisibility(View.INVISIBLE);
                favicon.setClickable(false);
            }
        });



        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notes.setVisibility(View.VISIBLE);
                addtonote.setVisibility(View.VISIBLE);

            }
        });

        addtonote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("User1").child("fav");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(album.getName())){
//                            notes.getText();
                            album.setThumbnail(1234);

                            DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("User").child("User1").child("fav").child(album.getName());
                            dRef.setValue(album);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}