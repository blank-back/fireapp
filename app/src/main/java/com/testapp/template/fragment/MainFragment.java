package com.testapp.template.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
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
import com.testapp.template.ui.MainActivity;
import org.pytorch.IValue;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private BottomSheetDialog bottomSheetDialog;
    private Bitmap pic;
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
                bottomSheetDialog.show();
                //GetPic();
            }
        });
        output=view.findViewById(R.id.Output);
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.bottomdialog);
        TextView camera=bottomSheetDialog.findViewById(R.id.camera);
        TextView album=bottomSheetDialog.findViewById(R.id.album);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCamera();
                bottomSheetDialog.dismiss();
            }
        });
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetPic();
                bottomSheetDialog.dismiss();
            }
        });
        upload_button=view.findViewById(R.id.mainbutton);
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariable globalVariable = GlobalVariable.getInstance();
                boolean login_state = globalVariable.getLoginState();
                if(login_state) {
                    //String base64_image = uri2base64(imageuri);
                    //output.setText(base64_image);
                    eval();
                    //output.setText("分类结果：短路，依据为头部特征与训练样本的相似度");
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

    public void GetPic() {
        Intent data=new Intent(Intent.ACTION_PICK);
        data.setType("image/*");
        startActivityForResult(data,1);
    }
    public void GetCamera() {
        File outputImage = new File(getActivity().getExternalCacheDir(),"output_image.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            if(outputImage.createNewFile());
            else {
                Toast.makeText(getActivity(), "创建缓存文件失败", Toast.LENGTH_LONG).show();
                return;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.d("imageuro",outputImage.toString());
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //imageuri=FileProvider.getUriForFile(getActivity(),"com.testapp.template.fileprovider",outputImage);
        //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        //startActivityForResult(intent,2);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 2);
        }
    }
    public String uri2base64(Uri uri)
    {
        if(uri==null)
            return "Nothing valuable for output";
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(this.imageuri));
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
            try {
                pic = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(this.imageuri));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(requestCode==2)
        {
            //Log.d("Picture",data.getData().toString());
            //imageuri=data.getData();
            Log.d("info",imageuri==null?"空的":"获取");
            Log.d("info",data.getExtras()==null?"空的extra":"获取");
            Bundle tmp=data.getExtras();
            //image_ul.setImageURI(imageuri);
            pic=(Bitmap) tmp.get("data");
            image_ul.setImageBitmap(pic);
            Log.d("info", String.valueOf((((Bitmap)tmp.get("data")).getWidth()))+","+String.valueOf((((Bitmap)tmp.get("data")).getHeight())));
            /*imageuri=data.getData();*/
        }
    }
    public void eval()
    {
        Bitmap bitMap = pic;
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        int newWidth = 224;
        int newHeight = 224;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        final Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitMap,TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);
        final Tensor outputTensor = ((MainActivity)getActivity()).model.forward(IValue.from(inputTensor)).toTensor();
        float shortcut=outputTensor.getDataAsFloatArray()[0];
        float burnt=outputTensor.getDataAsFloatArray()[1];
        Log.d("test",shortcut<burnt?"火烧":"短路");
        output.setText("识别结果："+(shortcut<burnt?("火烧，可信度："+burnt*100+"%"):("短路，可信度："+shortcut*100+"%")));
    }
}