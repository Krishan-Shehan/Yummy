package com.example.yummy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Instant;
import java.util.ArrayList;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "test.sliit.recyclerview.RecyclerViewAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;
    private Instant Glide;

    private LayoutInflater inflater;
    public static ArrayList<String> imageModelArrayList = new ArrayList<>() ;
    private Context ctx;

    public RecyclerViewAdapter(ArrayList<String> mImageNames) {
        this.mImageNames = mImageNames;

    }

//    public RecyclerViewAdapter( ArrayList<com.example.yummy.Swaping> imageModelArrayList) {
//
//        this.imageModelArrayList = imageModelArrayList;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ingredient_list, parent, false);
//        View view = inflater.inflate(R.layout.fragment_list, parent, false);
        return new ViewHolder(view);
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }

    @SuppressLint({"LongLogTag", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.checkBox.setText("Checkbox " + position);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.checkBox.getTag();
                Log.d("checkbox", mImageNames.get(position) + " clicked!");

                if(holder.checkBox.isChecked()){
                    imageModelArrayList.add(mImageNames.get(position));
                }else {
                    imageModelArrayList.remove(mImageNames.get(position));
                }
                Log.d("ck", String.valueOf(imageModelArrayList));
//                com.example.yummy.Swaping fruits1 = (com.example.yummy.Swaping)holder.checkBox.getTag();
//
//                fruits1.setSelected(holder.checkBox.isChecked());
//
//                list.get(position).setSelected(holder.checkBox.isChecked());
//
//                if (imageModelArrayList.get(pos).getSelected()) {
//                    imageModelArrayList.get(pos).setSelected(false);
//                } else {
//                    imageModelArrayList.get(pos).setSelected(true);
//                }
            }
        });



        holder.imageName.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("gg", "onClick: clicked on" + mImageNames.get(position));
//


            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        CircleImageView image;
        protected CheckBox checkBox;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
