package com.testapp.template.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.testapp.template.fragment.BlankFragment;


public class HelpFragmentAdapter extends FragmentPagerAdapter {

    private  String [] helppage;
    public HelpFragmentAdapter(FragmentManager fm,String [] helppage) {
        super(fm);
        this.helppage = helppage;
    }

    Fragment mFragment = null;

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                mFragment = new BlankFragment();
                break;
            case 1:
                mFragment = new BlankFragment();
                break;
        }
        return mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return helppage[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
