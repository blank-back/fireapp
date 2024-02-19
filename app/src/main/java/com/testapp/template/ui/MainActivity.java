package com.testapp.template.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import butterknife.BindString;
import com.testapp.template.GlobalVariable;
import com.testapp.template.R;
import com.testapp.template.base.BaseActivity;
import com.testapp.template.db.sqlitehelper;
import com.testapp.template.fragment.*;
import com.testapp.template.util.BottomNavigationViewHelper;
import com.testapp.template.util.ReadTxtFile;
import butterknife.BindView;

import java.io.*;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNav;

    @BindView(R.id.container)
    FrameLayout mContainer;

    @BindView(R.id.custom_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.slide_menu)
    NavigationView mslidemenu;

    @BindString(R.string.history_cache)
    String history_cache;

    private FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        Fragment mFragment = null;

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    mFragment = new OneFragment();
                    break;
                case 1:
                    mFragment = new TwoFragment();
                    break;
                case 2:
                    mFragment = new ThreeFragment();
                    break;
            }
            return mFragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
    private ActionBarDrawerToggle mToggle;
    public String filePath;
    public InputStream fis;
    public OutputStream fout;
    private sqlitehelper mySQLiteOpenHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initdb();
        filePath = Environment.getExternalStorageDirectory().getPath();
        GlobalVariable.getInstance().setsqlite(mySQLiteOpenHelper);
        GlobalVariable.getInstance().update();
        GlobalVariable.getInstance().setFilepath(filePath);
        //filePath = getFilesDir().getPath();
        if(!new File(filePath+"/"+R.string.cache_location).exists()) {
            new File(filePath + "/"+R.string.cache_location).mkdirs();
        }
        filePath+=("/"+R.string.cache_location);
        // 需要theme 设置成 NoActionBar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        // 关联左上角图标和侧滑菜单
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);
        BottomNavigationViewHelper.disableShiftMode(mBottomNav);
        mBottomNav.setOnNavigationItemSelectedListener(this);
        mBottomNav.setSelectedItemId(R.id.nav_one); // 设置默认
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        else
            Log.d("Vertify","Permission OK");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    private void initdb() {
        mySQLiteOpenHelper = new sqlitehelper(this);
        db = mySQLiteOpenHelper.getWritableDatabase();
        mySQLiteOpenHelper.onUpgrade(db,1,2);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        int index = 0;
        switch (item.getItemId()) {
            case R.id.nav_one:
                Log.e(TAG, "证物检测");
                mToolbar.setTitle("证物检测");
                index = 0;
                break;

            case R.id.nav_two:
                Log.e(TAG, "识别历史");
                mToolbar.setTitle("识别历史");
                index = 1;
                break;

            case R.id.nav_three:
                Log.e(TAG, "个人中心");
                mToolbar.setTitle("个人中心");
                index = 2;
                break;
        }
        // 判断是否持有过这个fragment 有直接返回，没有则创建
        // 该方法会调用setMenuVisibility 显示和隐藏

        // 设置为当前的fragment
        //mAdapter.setPrimaryItem(mContainer, 0, fragment);
        //空白填充，立即执行事务再显示三个主页面（由于自建页面设定为每次显隐都新建销毁，模板页面为首次建立后显隐，所以采取了摆烂写法）
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction temptr =fm.beginTransaction();
        temptr.replace(R.id.container,new BlankFragment());
        temptr.commit();
        fm.executePendingTransactions();
        fragment = (Fragment) mAdapter.instantiateItem(mContainer, index);
        mAdapter.setPrimaryItem(mContainer, 0, fragment);
        //temptr.replace(R.id.container,fragment);
        // 提交更新
        mAdapter.finishUpdate(mContainer);
        Log.d("info", filePath + "history");
        switch (item.getItemId()) {
            case R.id.nav_one:
                break;
            case R.id.nav_two:
                ReadTxtFile tool1 = new ReadTxtFile();
                String history1 = new String();
                File file4=new File(filePath+"/accinfo.txt");
                if(file4.exists()&&file4.length()!=0)
                    history1 = tool1.readFromFile(filePath+"/accinfo.txt");
                else {
                    history1 = "暂无历史";
                    if(file4.exists())
                        Log.d("info","history create");
                    else {
                        try {
                            FileWriter fw1 = new FileWriter(file4, false);
                            fw1.close();
                        }catch (Exception e)
                        {
                            Log.d("info","getex");
                        }
                        Log.d("info", filePath + "history failed");
                    }
                }
                TextView textView = findViewById(R.id.history_text);
                if (textView != null)
                    textView.setText(history1);
                //((TwoFragment)fragment).setText(history1);
                //((TwoFragment)mFragment).setText(history1);
                break;
            case R.id.nav_three:
                ReadTxtFile tool2 = new ReadTxtFile();
                File file5=new File(filePath+"/userinfo.txt");
                if(file5.exists()&&file5.length()!=0);
                else {
                    if(file5.exists())
                        Log.d("info","history create");
                    else {
                        try {
                            FileWriter fw1 = new FileWriter(file5, false);
                            fw1.close();
                        }catch (Exception e)
                        {
                            Log.d("info","getex");
                        }
                        Log.d("info", filePath + "history failed");
                    }
                }
            default:
                break;
        }

        return true;
    }

    public void clickslidmenu(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        HelpFragment helpfragment=new HelpFragment();
        /*TextView helptemp=(TextView)findViewById(R.id.help_text);
        helptemp.setText(R.string.help_doc);*/
        /*Bundle args=new Bundle();
        args.putString("text",slidemenu[i]);
        helpfragment.setArguments(args);*/
        switch (id)
        {
            case R.id.nav_info:
                mDrawerLayout.closeDrawers();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction temptr = fm.beginTransaction();
                temptr.replace(R.id.container,new HelpFragment());
                temptr.commit();
                fm.executePendingTransactions();
                break;
            case R.id.nav_about:
                mDrawerLayout.closeDrawers();
                FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction temptr1 = fm1.beginTransaction();
                temptr1.replace(R.id.container,new AboutFragment());
                temptr1.commit();
                fm1.executePendingTransactions();
                break;
            case R.id.nav_setting:
                mDrawerLayout.closeDrawers();
                SettingFragment sf;
                FragmentManager fm2 = getSupportFragmentManager();
                FragmentTransaction temptr2 = fm2.beginTransaction();
                temptr2.replace(R.id.container,sf=new SettingFragment());
                temptr2.commit();
                fm2.executePendingTransactions();
                sf.set_setting();
                break;
            default:
                break;
        }
    }

}
