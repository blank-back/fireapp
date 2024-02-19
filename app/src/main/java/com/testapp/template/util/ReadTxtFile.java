package com.testapp.template.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Switch;
import com.testapp.template.R;

import java.io.*;
import java.util.ArrayList;


public class ReadTxtFile {
    public ReadTxtFile()
    {

    }
    public String readFromFile(String file) {
        String a = new String();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder strb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                strb.append(line);
                strb.append("\n");
            }
            br.close();
            isr.close();
            fis.close();
            a = strb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
    public void writeIntoFile(ArrayList<String> a, String filepath, String filename)
    {
        String ret = "";
        File file1=new File(filepath,filename);
        try {
            FileWriter writer = new FileWriter(file1,true);
            for(int i=0;i<a.size();i++) {
                writer.write(a.get(i)+"\n");
            }
            writer.close(); // 关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
