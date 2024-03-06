package com.testapp.template.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;
import com.testapp.template.db.account;
import com.testapp.template.db.accounthelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button ret_btn;
    private Button searchbtn;
    private EditText accname;
    private TextView answ;
    public UsersearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersearchFragment newInstance(String param1, String param2) {
        UsersearchFragment fragment = new UsersearchFragment();
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
        View view=inflater.inflate(R.layout.fragment_usersearch, container, false);
        ret_btn = view.findViewById(R.id.manage_ret);
        searchbtn=view.findViewById(R.id.btn_search);
        answ=view.findViewById(R.id.search_answ);
        accname=view.findViewById(R.id.search_name);
        searchClick(view);
        retClick(view);
        return view;
    }
    public void searchClick(View view)
    {
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                account tmp=new accounthelper(getActivity()).getaccount(accname.getText().toString());
                if(tmp!=null)
                answ.setText("用户名："+tmp.getaccount()+"\n"+"权限级别："+(tmp.getmanager()?"管理员":"普通用户")+(tmp.getmanager()?"":"\n密码:"+tmp.getpassword()));
                else
                    answ.setText("不存在该用户");
            }
        });
    }
    public void retClick(View view)
    {
        ret_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                boolean manager=new accounthelper(getActivity()).getmanager(GlobalVariable.getInstance().getAccount());
                GlobalVariable.getInstance().setManager(manager);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction temptr =fm.beginTransaction();
                if(!manager) {
                    LoginalFragment2 frag = new LoginalFragment2();
                    temptr.replace(R.id.container, frag);
                }
                else
                {
                    ManagerFragment2 frag = new ManagerFragment2();
                    temptr.replace(R.id.container, frag);
                }
                temptr.commit();
                fm.executePendingTransactions();
            }
        });
    }
}