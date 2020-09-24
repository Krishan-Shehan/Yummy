package com.example.yummy;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class Ingredientst extends FragmentActivity {

    ViewPager viewPager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingtest);
        viewPager = findViewById(R.id.pager_1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));

    }
}

class MyAdapter extends FragmentPagerAdapter{


    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0){
            fragment= new FragmentA();
        }
        if (position == 1){
            fragment= new FragmentB();
        }
        if (position == 2){
            fragment= new FragmentC();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "page1";
        }
        if (position == 1){
            return "page2";
        }
        if (position == 2){
            return "page3";
        }
        return null;
    }
}