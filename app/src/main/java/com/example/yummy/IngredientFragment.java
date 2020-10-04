package com.example.yummy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment {

    View view;

    private RecyclerView recyclerView;

    public static IngredientFragment newInstance(int val,int f,int k) {
        IngredientFragment fragment = new IngredientFragment();

        if (k != 9 ){
            val = k;
            Bundle args = new Bundle();
            args.putInt("someInt", val);
            args.putInt("frame",f);
            Log.d("f", String.valueOf(f));
            fragment.setArguments(args);
            return fragment;
        }else {
            Bundle args = new Bundle();
            args.putInt("someInt", val);
            args.putInt("frame", f);
            fragment.setArguments(args);
            return fragment;
        }
    }

    int val;
    int f;
    int k;
    TextView c;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ingredient, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference("IngredientsCategory");
        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String ing = (String) dataSnapshot.getKey();
                    mNames.add(ing);
                }
                Log.d("testl", String.valueOf(mNames));
                Log.d("valll", val + "");

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String ing = (String) dataSnapshot.getValue();
                            mName.add(ing);
                        }

                        Log.d("testl", String.valueOf(mName));
                        recyclerView = view.findViewById(R.id.recycler_view);
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mName,f,mNames.get(val));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                };

//                if (val == 0) {
//                    DatabaseReference usersRef = databaseReference.child(mNames.get(val));
//                    Log.d("chilname", mNames.get(val));
//                    usersRef.addListenerForSingleValueEvent(eventListener);
//
//                }
//                if (val == 1) {
//                    DatabaseReference usersRef = databaseReference.child(mNames.get(val));
//                    Log.d("chilname", mNames.get(val));
//                    usersRef.addListenerForSingleValueEvent(eventListener);
//                }
//                if (val == 2) {
//                    DatabaseReference usersRef = databaseReference.child(mNames.get(val));
//                    Log.d("chilname", mNames.get(val));
//                    usersRef.addListenerForSingleValueEvent(eventListener);
//                }
//                if (val == 3) {
//                    DatabaseReference usersRef = databaseReference.child(mNames.get(val));
//                    usersRef.addListenerForSingleValueEvent(eventListener);
//                }
                DatabaseReference usersRef;
                switch (val) {
                    case 0:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 1:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 2:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 3:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 4:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 5:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 6:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 7:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 8:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                    case 9:
                        usersRef = databaseReference.child(mNames.get(val));
                        Log.d("chilname", mNames.get(val));
                        usersRef.addListenerForSingleValueEvent(eventListener);
                        break;
                }


//                for(int l = 0;l<mNames.size();l++){
//                    DatabaseReference usersRef = databaseReference.child(mNames.get(l));
//
//                }

//                c.setText("test1");
//                recyclerView = view.findViewById(R.id.recycler_view);
//                RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames);
//                recyclerView.setAdapter(adapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                c.setText("" + val);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        val = getArguments().getInt("someInt", 0);
        f = getArguments().getInt("frame",0);
        Log.d("val", val + "");
        databaseReference.addListenerForSingleValueEvent(valueEventListener);


//        if (val == 0) {
//            databaseReference.addListenerForSingleValueEvent(valueEventListener);
//        }
//        if (val == 1) {
//            usersRef2.addListenerForSingleValueEvent(valueEventListener);
//        }

//        modelArrayList = getModel(false);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(modelArrayList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}