package com.testapp.template.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import static android.util.Base64.DEFAULT;
import static android.util.Base64.encodeToString;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    private ImageView image_ul;
    private Uri imageuri;
    private TextView output;
    private Button upload_button;
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        image_ul=view.findViewById(R.id.imageView);
        image_ul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetPic(getView());
            }
        });
        output=view.findViewById(R.id.Output);
        upload_button=view.findViewById(R.id.mainbutton);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable globalVariable = GlobalVariable.getInstance();
                boolean login_state = globalVariable.getLoginState();
                if(login_state) {
                    String base64_image = uri2base64(imageuri);
                    output.setText(base64_image);
                }
                else
                {
                    Toast.makeText(getActivity(),"请前往个人中心进行登录",Toast.LENGTH_SHORT).show();
                    /*FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction temptr =fm.beginTransaction();
                    temptr.replace(R.id.viewpager,new MainFragment());
                    temptr.commit();
                    fm.executePendingTransactions();*/
                }
            }
        });
        return view;
    }

    public void GetPic(View view) {
        Intent data=new Intent(Intent.ACTION_PICK);
        data.setType("image/*");
        startActivityForResult(data,1);
    }
    public String uri2base64(Uri uri)
    {
        if(uri==null)
            return "Nothing valuable for output";
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(this.imageuri));
        } catch (Exception e) {
            e.printStackTrace();
        }
                String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                //压缩图片至100kb
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                //接收压缩的图片数据流，并转换成base64编码
                byte[] bitmapBytes = baos.toByteArray();
                result = encodeToString(bitmapBytes, DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(data==null) {
            Toast.makeText(getActivity(),"未选择任何需要识别的图片",Toast.LENGTH_SHORT).show();
            imageuri=null;
            return;
        }
        if(requestCode==1)
        {
            Toast.makeText(getActivity(),data.getData().toString(),Toast.LENGTH_SHORT).show();
            Log.d("Picture",data.getData().toString());
            image_ul.setImageURI(data.getData());
            imageuri=data.getData();
        }
    }
}