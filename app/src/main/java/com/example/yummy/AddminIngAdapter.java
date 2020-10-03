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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddminIngAdapter extends RecyclerView.Adapter<AddminIngAdapter.ViewHolder> {

    private Context mContext;
    ArrayList<String> ingredients;

//    public static List<Album> rep = new ArrayList<>();



    public AddminIngAdapter(Context mContext, ArrayList<String> ingredients) {
        this.mContext = mContext;
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingitemeditdelete, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String x = ingredients.get(position);
        holder.editing.setText(x);
    }


    @Override
    public int getItemCount() {

        Log.d("size", String.valueOf(ingredients.size()));
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView editing;
        Button edit,delete;

        public ViewHolder(View itemView) {
            super(itemView);
            editing = itemView.findViewById(R.id.editing);
//            edit = itemView.findViewById(R.id.editinglist);
//            delete = itemView.findViewById(R.id.deleteing);
        }
    }
}
