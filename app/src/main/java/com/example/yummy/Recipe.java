package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yummy.Model.Onlineuser;
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
    Recipeclass recipeclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeclass = AlbumsAdapter.rep.get(0);
        AlbumsAdapter.rep.remove(0);

        Log.d("albumre", String.valueOf(recipeclass));

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.recipeview);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeAdapter(this, recipeclass);
        recyclerView.setAdapter(adapter);

        favicon = findViewById(R.id.addtofav);
        addnote = findViewById(R.id.addnote);
        notes = findViewById(R.id.notes);
        addtonote = findViewById(R.id.addtonote);
        Mname = findViewById(R.id.Mname);

        Mname.setText(recipeclass.getName());

        favicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                favicon.setCompoundDrawables(Drawable.createFromPath("@drawable/ic_baseline_add_circle_outline_24"),null,null,null);
//                Drawable[] x = favicon.getCompoundDrawables();
//                Log.d("draw", String.valueOf(x));
//                Drawable.createFromPath("@drawable/ic_baseline_add_circle_outline_24");
                Log.d("t1","click fav");
                DatabaseReference def = FirebaseDatabase.getInstance().getReference("User").child(Onlineuser.onlineuser.getPhone()).child("favorites");
                try{

                    def.child(recipeclass.getName()).setValue(recipeclass);
//                    Log.d("ll", String.valueOf(recipeclass));

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
                        if (snapshot.hasChild(recipeclass.getName())){
//                            notes.getText();
                            recipeclass.setNote(notes.getText().toString());

                            DatabaseReference dRef = FirebaseDatabase.getInstance().getReference("User").child("User1").child("fav").child(recipeclass.getName());
                            dRef.setValue(recipeclass);
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