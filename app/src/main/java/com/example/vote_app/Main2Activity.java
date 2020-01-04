package com.example.vote_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.vote_app.UI.Me.MeFragment;
import com.example.vote_app.UI.VoteList.VoteListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        initBottomNav();

    }

    int[] ivTabs;
//    String[] tvTabs;

    private void initBottomNav(){
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new VoteListFragment());
        fragmentList.add(new MeFragment());

        viewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        ivTabs = new int[]{R.drawable.vote, R.drawable.me};
//        tvTabs = new String[]{"列表","我"};

        for(int i=0;i<fragmentList.size();i++){
            View view = LayoutInflater.from(this).inflate(R.layout.main_tab_item, null, false);
            ImageView ivTab = view.findViewById(R.id.ivTab);
//            TextView tvTab = view.findViewById(R.id.tvTab);

            ivTab.setImageResource(ivTabs[i]);
//            tvTab.setText(tvTabs[i]);
            if(i==0){
                ivTab.setColorFilter(Color.GREEN);
//                tvTab.setTextColor(Color.GREEN);
            }else{
                ivTab.setColorFilter(Color.WHITE);
//                tvTab.setTextColor(Color.WHITE);
            }
            Objects.requireNonNull(tabLayout.getTabAt(i)).setCustomView(view);
        }
        tabLayout.setSelectedTabIndicatorHeight(0);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ImageView iv = customView.findViewById(R.id.ivTab);
//                TextView tv = customView.findViewById(R.id.tvTab);
                iv.setColorFilter(Color.GREEN);
//                tv.setTextColor(Color.GREEN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                ImageView iv = customView.findViewById(R.id.ivTab);
//                TextView tv = customView.findViewById(R.id.tvTab);
                iv.setColorFilter(Color.WHITE);
//                tv.setTextColor(Color.WHITE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
