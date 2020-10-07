package com.example.yummy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter<FavoritesRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List<Recipeclass> recipeclassList;
//    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;


    public FavoritesRecyclerViewAdapter(Context context,List<Recipeclass> recipeclassList){
        this.recipeclassList = recipeclassList;
        mContext = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("favc","onBindViewHolder : cld");
        final Recipeclass recipeclass = recipeclassList.get(position);
        holder.name.setText(recipeclass.getName());
    }


//    @Override
//    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        Log.d(TAG, "onBindViewHolder: called.");
//
//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImageUrls.get(position))
//                .into(holder.image);
//
//        holder.name.setText(mNames.get(position));
//
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
//                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return recipeclassList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.title);
        }
    }

}
