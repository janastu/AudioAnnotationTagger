package annotation.servelots.audiotaganotation;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends Activity {
    TextView tv;
    String[] id, uploadDate, url;
    String[][] tags;
    String temp;
    int[] i;
    JsonParse jp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy tp = StrictMode.ThreadPolicy.LAX;
        StrictMode.setThreadPolicy(tp);
        jp = new JsonParse();

        id = new String[jp.numberOfFiles()];
        uploadDate = new String[jp.numberOfFiles()];
        url = new String[jp.numberOfFiles()];
        tags = new String[jp.numberOfFiles()][100];
       // i = new int[jp.numberOfFiles()];

        id = jp.StringDataGetter("id");
        uploadDate = jp.StringDataGetter("uploadDate");
        url = jp.StringDataGetter("url");
       // i = jp.TagDataGetter();


        tv = (TextView) findViewById(R.id.tv);
        for (int j = 0; j < jp.numberOfFiles(); j++) {

           // temp = temp + "   :   " +i[j];
        }
        tv.setText(temp);


    }


}