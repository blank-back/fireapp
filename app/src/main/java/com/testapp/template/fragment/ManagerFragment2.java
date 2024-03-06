package com.testapp.template.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginalFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagerFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView acctext;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManagerFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginalFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ManagerFragment2 newInstance(String param1, String param2) {
        ManagerFragment2 fragment = new ManagerFragment2();
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
        View view=inflater.inflate(R.layout.fragment_manager2, container, false);
        acctext=view.findViewById(R.id.managername);
        acctext.setText(GlobalVariable.getInstance().getAccount());
        Button btn=view.findViewById(R.id.btn_mleave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                GlobalVariable.getInstance().setLoginState(false);
                GlobalVariable.getInstance().setAccount("");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction temptr =fm.beginTransaction();
                ForloginFragment2 frag=new ForloginFragment2();
                temptr.replace(R.id.container,frag);
                temptr.commit();
                fm.executePendingTransactions();
            }
        });
        Button managebtn=view.findViewById(R.id.user_manage);
        Button searchbtn=view.findViewById(R.id.user_search);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction temptr =fm.beginTransaction();
                UsersearchFragment frag=new UsersearchFragment();
                temptr.replace(R.id.container,frag);
                temptr.commit();
                fm.executePendingTransactions();
            }
        });
        managebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction temptr =fm.beginTransaction();
                UsermanageFragment frag=new UsermanageFragment();
                temptr.replace(R.id.container,frag);
                temptr.commit();
                fm.executePendingTransactions();
            }
        });
        return view;
    }
    public void setText(String accname)
    {
        acctext.setText(accname);
    }
}