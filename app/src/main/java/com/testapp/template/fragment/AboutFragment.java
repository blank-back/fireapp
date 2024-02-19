package com.testapp.template.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindArray;
import butterknife.BindView;
import com.testapp.template.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*@BindView(R.id.help_text)
    TextView helptext;*/
    private static int build = 0;
    private static int fragid = 0;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView icon;
    private TextView version;
    private TextView dev;
    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment helpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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
        if(build==0) {
            build = 1;
            fragid=this.getId();
        }
        //对称布局，marginRight不明原因失效因而绕了远路
        final View view=inflater.inflate(R.layout.fragment_about, container, false);
        final View view1=view;
        icon=view.findViewById(R.id.about_icon);
        dev=view.findViewById(R.id.about_dev);
        version=view.findViewById(R.id.about_version);
        view.post(new Runnable() {
            @Override
            public void run() {
                int width = view1.getWidth();
                int version_width=version.getWidth();
                Log.d("view width",new String(String.valueOf(width)));
                Log.d("view height",new String(String.valueOf(view1.getHeight())));
                Log.d("dev width",new String(String.valueOf(version_width)));
                Log.d("dev height",new String(String.valueOf(version.getHeight())));
                ViewGroup.MarginLayoutParams param_icon = (ViewGroup.MarginLayoutParams) icon.getLayoutParams();
                param_icon.setMarginStart(200);
                icon.setLayoutParams(param_icon);
                ViewGroup.MarginLayoutParams param_dev = (ViewGroup.MarginLayoutParams) dev.getLayoutParams();
                param_dev.setMarginStart(width-200-version_width);
                dev.setLayoutParams(param_dev);
                ViewGroup.MarginLayoutParams param_ver = (ViewGroup.MarginLayoutParams) version.getLayoutParams();
                param_ver.setMarginStart(width-200-version_width);
                version.setLayoutParams(param_ver);
            }
        });
        //helptext.setText(R.string.help_doc);

        return view;
    }

    public static boolean ifcreate()
    {
        return (build!=0);
    }

    public static int getfragid()
    {
        return fragid;
    }
}