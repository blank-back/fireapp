import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class ReadTxtFile {
    public ReadTxtFile()
    {

    }
    public String readFromFile(Context context, String filename) {
        String ret = "";
        try {
            FileInputStream fis = context.openFileInput(filename);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                fis.close();
                ret = stringBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
