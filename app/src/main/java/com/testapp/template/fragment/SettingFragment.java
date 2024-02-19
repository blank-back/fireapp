package com.testapp.template.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;
import com.testapp.template.db.settings;
import com.testapp.template.db.settingshelper;
import com.testapp.template.util.ReadTxtFile;

import java.io.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private settingshelper helper;
    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        helper=new settingshelper(getActivity());
        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        Button button=view.findViewById(R.id.setting_savebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!GlobalVariable.getInstance().getLoginState())
                {
                    Toast.makeText(getActivity(),"请前往个人中心进行登录",Toast.LENGTH_SHORT).show();
                    return;
                }
                Switch tmp1=view.findViewById(R.id.setting_switch1);
                Switch tmp2=view.findViewById(R.id.setting_switch2);
                Switch tmp3=view.findViewById(R.id.setting_switch3);
                helper.crat(new settings(GlobalVariable.getInstance().getAccount(),tmp1.isChecked(),tmp2.isChecked(),tmp3.isChecked()));
            }
        });
        return view;
    }



    //加载设置
    public void set_setting()
    {
        settings tmp=helper.getsetting(GlobalVariable.getInstance().getAccount());
        Log.d("set_settings",tmp.getaccount());
        ((Switch)getView().findViewById(R.id.setting_switch1)).setChecked(tmp.gettest1());
        ((Switch)getView().findViewById(R.id.setting_switch2)).setChecked(tmp.gettest2());
        ((Switch)getView().findViewById(R.id.setting_switch3)).setChecked(tmp.gettest3());
    }
}