package com.testapp.template.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;
import com.testapp.template.db.account;
import com.testapp.template.db.accounthelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsermanageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsermanageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private Button commit_btn;
    private Button delete_btn;
    private Button history_btn;
    private Button ret_btn;
    private EditText username;
    private EditText password;
    public UsermanageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsermanageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsermanageFragment newInstance(String param1, String param2) {
        UsermanageFragment fragment = new UsermanageFragment();
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
        View view=inflater.inflate(R.layout.fragment_usermanage, container, false);
        commit_btn = view.findViewById(R.id.btn_commit);
        delete_btn = view.findViewById(R.id.btn_delete);
        ret_btn = view.findViewById(R.id.manage_ret);
        history_btn = view.findViewById(R.id.btn_history);
        spinner = view.findViewById(R.id.input_auth);
        username = view.findViewById(R.id.input_name);
        password = view.findViewById(R.id.input_pwd);
        //TODO:值得注意，若用户未输入内容则默认不变更相应信息
        // 创建适配器并设置数据源
        String[] data = {"管理员", "普通用户"};
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);

        // 设置下拉框样式
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 设置适配器
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(1);
        commitClick(view);
        clearClick(view);
        deleteClick(view);
        retClick(view);
        return view;
    }
    public void commitClick(View view)
    {
        commit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if(new accounthelper(getActivity()).crat(new account(username.getText().toString(),android.text.TextUtils.isEmpty(password.getText())?"":password.getText().toString(),spinner.getSelectedItemPosition()==0)))
                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void clearClick(View view)
    {
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO：实际实现清空历史
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                new AlertDialog.Builder(getActivity()).setTitle("信息提示")//设置对话框标题
                        .setMessage("是否确认清空历史？（数据将无法恢复）")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                Toast.makeText(getActivity(),"清空成功",Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件

                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }
    public void deleteClick(View view)
    {
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                new AlertDialog.Builder(getActivity()).setTitle("信息提示")//设置对话框标题
                        .setMessage("是否确认删除用户？（数据将无法恢复）")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            //TODO：实际实现删除
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件

                    }
                }).show();//在按键响应事件中显示此对话框
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