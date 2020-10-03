package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Favorites extends AppCompatActivity {

    private static final String TAG = "Favorites";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private List<Album> albumList;
    private FavoritesRecyclerViewAdapter adapter;
    private AlbumsAdapter adapter2;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView,recyclerView2;

    Album album,t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

//        Log.d("log", (String) getIntent().getSerializableExtra("album"));

//        t1 = AlbumsAdapter.rep.get(0);
//        Log.d("t1", String.valueOf(t1));
//        AlbumsAdapter.rep.remove(0);

        albumList = new ArrayList<>();
        this.album = new Album();

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView2 = findViewById(R.id.favoritesedited);

        recyclerView2.setLayoutManager(layoutManager);
        adapter2 = new AlbumsAdapter( Favorites.this,albumList);
        recyclerView2.addItemDecoration(new RecipeList.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter2);

        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.favoritesSlider);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new FavoritesRecyclerViewAdapter(this,albumList);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child("User1").child("fav");

        databaseReference.addListenerForSingleValueEvent(eventListener);

    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                album = dataSnapshot.getValue( Album.class );
                albumList.add(album);
                Log.d("albumc",album.getName());
                Log.d("albumc",String.valueOf(album.getThumbnail()));
            }
            Log.d("albumc",String.valueOf(album.getThumbnail()));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

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