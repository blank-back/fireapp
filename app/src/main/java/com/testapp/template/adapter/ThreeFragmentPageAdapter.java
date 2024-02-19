package com.testapp.template.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.util.Log;
import com.testapp.template.GlobalVariable;
import com.testapp.template.fragment.BlankFragment;
import com.testapp.template.fragment.ForloginFragment2;
import com.testapp.template.fragment.LoginalFragment2;
import com.testapp.template.fragment.MainFragment;

/**
 * Created by testapp.
 */

public class ThreeFragmentPageAdapter extends FragmentPagerAdapter {

    private  String [] mTitles;
    public ThreeFragmentPageAdapter(FragmentManager fm,String [] titles) {
        super(fm);
        this.mTitles = titles;
    }

    Fragment mFragment = null;

    @Override
    public Fragment getItem(int position) {
        Log.d("getitem","reached");
        if(GlobalVariable.getInstance().getLoginState()) {
            mFragment = new LoginalFragment2();
            Log.d("getitem","loginal");
        }
        else{
                mFragment = new ForloginFragment2();
            Log.d("getitem","forlogin");
        }
        return mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
