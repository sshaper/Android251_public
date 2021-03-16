package com.scottshaper.tablayoutdemov2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
            public TabPagerAdapter(FragmentManager fm, int numberOfTabs){
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = numberOfTabs;
            }

            public Fragment getItem(int position){
                switch (position){
                    case 0: return new FirstFragment();
                    case 1: return new SecondFragment();
                    case 2: return new ThirdFragment();
                    default: return null;
                }
            }
            @Override
            public int getCount(){
                return tabCount;
            }
}
