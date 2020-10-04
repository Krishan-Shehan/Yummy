package com.example.yummy;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PlansPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    int f;
    int k;

    public PlansPagerAdapter(FragmentManager fm, int NumOfTabs,int f,int k) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.f = f;
        this.k = k;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return IngredientFragment.newInstance(position,f,k);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
