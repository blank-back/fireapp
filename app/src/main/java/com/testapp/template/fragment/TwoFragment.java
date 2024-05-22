package com.testapp.template.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;

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

    public void setDate(String text){
        TextView textView = (TextView) getView().findViewById(R.id.datetime);
        textView.setText(text);
    }

    public void setAnswer(String text){
        TextView textView = (TextView) getView().findViewById(R.id.answertext);
        textView.setText(text);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_two, container, false);
        TextView date = (TextView) view.findViewById(R.id.datetime);
        TextView answertext = (TextView) view.findViewById(R.id.answertext);
        WindowManager wm = getActivity().getWindowManager();
        Display d = wm.getDefaultDisplay();
        ViewGroup.LayoutParams l = date.getLayoutParams();
        l.width=d.getWidth()/2;
        l.height=d.getHeight()/2;
        ViewGroup.LayoutParams v = answertext.getLayoutParams();
        v.width=d.getWidth()/2;
        v.height=d.getHeight()/2;
        return view;
    }

    @Override
    protected void initListener(View view) {

    }
}
