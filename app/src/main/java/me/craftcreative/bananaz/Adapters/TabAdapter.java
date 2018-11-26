package me.craftcreative.bananaz.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {

    // LIST OF FRAGMENTS AND TAB NAMES
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitle = new ArrayList<>();
    //CONSTRUCTOR THAT GETS THE FRAGMENT INSTANCE
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    //GETTING FRAGMENT AND ITS TAB
    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitle.add(title);
    }

    //GET PAGE TITLE POSITION
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitle.get(position);
    }

    //SIZE OF LIST
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
