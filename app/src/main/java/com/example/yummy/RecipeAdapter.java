package com.example.yummy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Album album;
    private Context mContext;

    public  RecipeAdapter(Context context,Album album){
        this.album = album;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_recipe, parent, false);
        return new RecipeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        holder.recipename.setText(album.getName());
        Log.d("albumrname",album.getName());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView recipename, ingredients, portions, details;

        public ViewHolder(View itemView) {
            super(itemView);
            recipename = itemView.findViewById(R.id.recipename);
            ingredients = itemView.findViewById(R.id.ingredients);
            portions = itemView.findViewById(R.id.portions);
            details = itemView.findViewById(R.id.recipedetails);
        }
    }
}
