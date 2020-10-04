package com.example.yummy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public static ArrayList<String> imList = new ArrayList<>() ;
    private Context ctx;

    private int f;
    private String child;
    public RecyclerViewAdapter(ArrayList<String> mImageNames, int f, String s) {
        this.mImageNames = mImageNames;
        this.f = f;
        this.child = s;

    }

//    public RecyclerViewAdapter( ArrayList<com.example.yummy.Swaping> imageModelArrayList) {
//
//        this.imageModelArrayList = imageModelArrayList;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
             view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ingredient_list, parent, false);
//        View view = inflater.inflate(R.layout.fragment_list, parent, false);
            return new ViewHolder(view);

//        return new ViewHolder(view);
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }

    @SuppressLint({"LongLogTag", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if(f == 0){
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
        }else {

            holder.imageName.setText(mImageNames.get(position));
            holder.checkBox.setVisibility(View.INVISIBLE);
            holder.Deleteingname.setVisibility(View.VISIBLE);
            holder.editingrediantname.setVisibility(View.VISIBLE);

            holder.Deleteingname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    imList = new ArrayList<>();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("IngredientsCategory").child(child);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                imList.add((String) dataSnapshot.getValue());
                            }
                            Log.d("ki", String.valueOf(imList));
                            int x =imList.indexOf(imList.get(position));
                            imList.remove(x);
                            Log.d("ki", String.valueOf(imList));
                            DatabaseReference def = FirebaseDatabase.getInstance().getReference("IngredientsCategory").child(child);
                            def.setValue(imList);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//                    Intent intent  = new Intent(mContext,AddIngredientCategory.class);
//                    mContext.startActivity(intent);


                }
            });

            holder.editingrediantname.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    imageModelArrayList.add(mImageNames.get(position));
                    Log.d("ck", String.valueOf(imageModelArrayList));
                    holder.bsave.setVisibility(View.VISIBLE);
                    holder.editt.setVisibility(View.VISIBLE);
                    holder.editt.setHint(mImageNames.get(position));
                    holder.bsave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            imList = new ArrayList<>();
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("IngredientsCategory").child(child);
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                                        imList.add((String) dataSnapshot.getValue());
                                    }
                                        Log.d("ki", String.valueOf(imList));

                                    int x =imList.indexOf(imList.get(position));
                                    imList.remove(x);
                                    imList.add(x,holder.editt.getText().toString().trim());
                                    Log.d("ki", String.valueOf(imList));
                                    DatabaseReference def = FirebaseDatabase.getInstance().getReference("IngredientsCategory").child(child);
                                    def.setValue(imList);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });


                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        CircleImageView image;
        protected CheckBox checkBox;
        TextView imageName,editing;
        RelativeLayout parentLayout;
        Button Deleteingname,editingrediantname,bsave;
        EditText editt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            editing = itemView.findViewById(R.id.editing);
            Deleteingname = itemView.findViewById(R.id.Deleteingname);
            editingrediantname = itemView.findViewById(R.id.editingrediantname);
            bsave = itemView.findViewById(R.id.sbtn);
            editt = itemView.findViewById(R.id.editi);
        }
    }
}
