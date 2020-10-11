package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Recipeclass> recipeclassList;
    public List<String> ingredients = new ArrayList<>();
    Recipeclass recipeclass;
    ImageView profilego;
    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        try {
            admin  = getIntent().getExtras().getString("Radmin");
        }catch (Exception e){

        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        profilego = findViewById(R.id.profilego_logo);
        profilego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeList.this,Home.class);
                startActivity(intent);
            }
        });
        recipeclassList = new ArrayList<>();

        ingredients.add("True");
        ingredients.add("Romance");


        recipeclass = new Recipeclass();

//        prepareAlbums();
        if (admin.equals("R")){
            adapter = new AlbumsAdapter(RecipeList.this, recipeclassList,"fav");
        }else{
            adapter = new AlbumsAdapter(RecipeList.this, recipeclassList,"");
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(RecipeList.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

//        Query query1 = recipeRef.where("ingredients.tomato", "==", true).where("ingredients.pepper", "==", true).where("ingredients.cheese", "==", true);


//        Query query = FirebaseDatabase.getInstance().getReference("Recipeclass")
//                .orderByChild("test")
//                .equalTo("kk");
//                .startAt("Romance")
//                .endAt("Romance"+"\uf8ff");

        databaseReference.addListenerForSingleValueEvent(valueEventListener);

//        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                recipeclass = dataSnapshot.getValue( Recipeclass.class );
                recipeclassList.add(recipeclass);
                Log.d("albumc", recipeclass.getName());
                Log.d("albumc",String.valueOf(recipeclass.getThumbnail()));
            }
            Log.d("recipeclass", String.valueOf(recipeclassList));
            adapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    };

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */

    /**
     * Adding few albums for testing
     */
//    private void prepareAlbums() {
//        int[] covers = new int[]{
//                R.drawable.album1,
//                R.drawable.album2,
//                R.drawable.album3,
//                R.drawable.album4,
//                R.drawable.album5,
//                R.drawable.album6,
//                R.drawable.album7,
//                R.drawable.album8,
//                R.drawable.album9,
//                R.drawable.album10,
//                R.drawable.album11};

//        Recipeclass a = new Recipeclass("True Romance", covers[0]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Xscpae",covers[1]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Maroon 5",  covers[2]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Born to Die",  covers[3]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Honeymoon",  covers[4]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("I Need a Doctor", covers[5]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Loud",  covers[6]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Legend",  covers[7]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Hello", covers[8]);
//        recipeclassList.add(a);
//
//        a = new Recipeclass("Greatest Hits",covers[9]);
//        recipeclassList.add(a);
//
//        adapter.notifyDataSetChanged();
//    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}