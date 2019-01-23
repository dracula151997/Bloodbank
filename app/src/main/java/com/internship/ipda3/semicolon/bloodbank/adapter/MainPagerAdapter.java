package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.internship.ipda3.semicolon.bloodbank.ui.fragment.ArticleFragment;
import com.internship.ipda3.semicolon.bloodbank.ui.fragment.BloodRequestFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new ArticleFragment();
            case 1:
                return new BloodRequestFragment();
            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "المقالات";
            case 1:
                return "طلبات التبرع";
        }
        return super.getPageTitle(position);
    }
}
