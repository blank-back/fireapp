package com.testapp.template.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.testapp.template.R;
import com.testapp.template.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment {

    public TwoFragment() {
        // Required empty public constructor
    }

    public void setText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.history_text);
        textView.setText(text);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }

    @Override
    protected void initListener(View view) {

    }
}
