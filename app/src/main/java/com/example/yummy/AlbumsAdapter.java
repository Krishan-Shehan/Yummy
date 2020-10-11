package com.example.yummy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Onlineuser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Recipeclass> recipeclassList;
    public static List<Recipeclass> rep = new ArrayList<>();
    private String fav;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail, overflow;
        public Button button;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
//            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            button = view.findViewById(R.id.fav);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter(Context mContext, List<Recipeclass> recipeclassList,String fav) {
        this.mContext = mContext;
        this.recipeclassList = recipeclassList;
        this.fav = fav;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Recipeclass recipeclass = recipeclassList.get(position);

        if (fav.equals("fav")){
            holder.button.setText("Delete");
        }

        holder.title.setText(recipeclass.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipeclass recipeclass1 = recipeclass;

                rep.add(recipeclass1);
                Log.d("rep", String.valueOf(rep));


                Intent intent = new Intent(mContext, Recipe.class);
                mContext.startActivity(intent);

            }
        });
        holder.thumbnail.setBackgroundResource(recipeclass.getThumbnail());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("fav", recipeclass.getName());

                DatabaseReference def = FirebaseDatabase.getInstance().getReference("Users").child(Onlineuser.onlineuser.getPhone()).child("favorites");
                if (fav.equals("fav")){
                    def.child(recipeclass.getName()).removeValue();
                }else {
                    try {

                        def.child(recipeclass.getName()).setValue(recipeclass);
//                    Log.d("ll", String.valueOf(recipeclass));

                    } catch (Exception e) {

                    }
                }

            }
        });

//        holder.count.setText(recipeclass.getNumOfSongs() + " songs");

//        Glide.with(mContext).load(recipeclass.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return recipeclassList.size();
    }
}
