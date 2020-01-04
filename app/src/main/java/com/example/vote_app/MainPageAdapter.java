package com.example.vote_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: ITryagain
 * created on: 2020/1/3 19:52
 * description:
 */
public class MainPageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = null;

    public MainPageAdapter(FragmentManager fm){
        super(fm);
    }

    public MainPageAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
