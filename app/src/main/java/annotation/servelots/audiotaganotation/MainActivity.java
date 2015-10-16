package annotation.servelots.audiotaganotation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    String[] id, uploadDate, url;
    JsonParse jp;
    LinearLayout llParentLayout;


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


        id = jp.StringDataGetter("id");
        uploadDate = jp.StringDataGetter("uploadDate");
        url = jp.StringDataGetter("url");

        llParentLayout = (LinearLayout) findViewById(R.id.llParentLayout);


        MakeViews(jp.numberOfFiles());

    }

    public void MakeViews(int num) {
        int j;

        for (j = 0; j < num; j++) {
            final int index = j;
            // Container Layout
            LinearLayout containerLayout = new LinearLayout(this);
            containerLayout.setOrientation(LinearLayout.VERTICAL);

            containerLayout.setGravity(Gravity.CENTER);
            Resources res = getResources(); // resource handle
            Drawable drawable = res.getDrawable(R.drawable.card);
            containerLayout.setBackgroundDrawable(drawable);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(30, 20, 30, 0);


            Button btnTitle = new Button(this);
            btnTitle.setText(id[j]);
            btnTitle.setGravity(Gravity.CENTER);
            btnTitle.setWidth(AbsListView.LayoutParams.WRAP_CONTENT);
            btnTitle.setHeight(AbsListView.LayoutParams.WRAP_CONTENT);
            containerLayout.addView(btnTitle);
            llParentLayout.addView(containerLayout, layoutParams);

            btnTitle.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GoToPlayer(id[index], index, url[index], uploadDate[index]);
                }
            });


        }
    }

    public void GoToPlayer(String s2, int index, String s, String s1) {

        Intent i;
        i = new Intent(MainActivity.this, AudioPlayer.class);
        i.putExtra("indexKey", String.valueOf(index));
        i.putExtra("dateKey", s1);
        i.putExtra("urlKey", s);
        i.putExtra("idKey", s2);
        startActivity(i);


    }
}