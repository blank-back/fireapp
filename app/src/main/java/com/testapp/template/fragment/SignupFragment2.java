package com.testapp.template.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;
import com.testapp.template.db.ReadDatabase;
import com.testapp.template.db.account;
import com.testapp.template.db.accounthelper;
import com.testapp.template.util.ReadTxtFile;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SQLiteDatabase db;
    public SignupFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment2 newInstance(String param1, String param2) {
        SignupFragment2 fragment = new SignupFragment2();
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
        db= GlobalVariable.getInstance().getsqlite().getWritableDatabase();
        View view=inflater.inflate(R.layout.fragment_signup2, container, false);
        Button btn=view.findViewById(R.id.btn_snup);
        final EditText acc=view.findViewById(R.id.sign_name);
        final EditText pwd=view.findViewById(R.id.sign_pwd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if(!new accounthelper(getActivity()).crat(new account(acc.getText().toString(),pwd.getText().toString())))
                {
                    Toast.makeText(getActivity(),"注册失败，账户名重复",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn1=view.findViewById(R.id.btn_exitsign);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction temptr =fm.beginTransaction();
                ForloginFragment2 frag=new ForloginFragment2();
                temptr.replace(R.id.container,frag);
                temptr.commit();
                fm.executePendingTransactions();
            }
        });
        return view;
    }
}