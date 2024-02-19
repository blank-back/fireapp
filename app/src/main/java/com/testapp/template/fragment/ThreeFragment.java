package com.testapp.template.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import com.testapp.template.R;
import com.testapp.template.adapter.ThreeFragmentPageAdapter;
import com.testapp.template.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends BaseFragment {

    @BindView(R.id.loginpager)
    ViewPager mViewPager;
    private ThreeFragmentPageAdapter mAdapter;
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    protected void initListener(View view) {
        String[] mtitles={"forlogin","loginal"};
        mAdapter = new ThreeFragmentPageAdapter(getChildFragmentManager(),mtitles);
        mViewPager.setAdapter(mAdapter);
    }

}
