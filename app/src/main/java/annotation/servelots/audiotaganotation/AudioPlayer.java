package annotation.servelots.audiotaganotation;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by feanor on 10/15/2015.
 */
public class AudioPlayer extends Activity implements View.OnClickListener, View.OnTouchListener {

    TextView tvTitle, tvUploadDate, tv;
    MediaPlayer mp;
    SeekBar seekBar;

    Handler handler;

    String url, uploadDate, id;
    String[] qwe;
    int num;
    Intent i;
    Button btnPlay, btnPause, btnStop;
    JsonParse jp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.audio_player);
        handler = new Handler();

        i = getIntent();
        url = i.getStringExtra("urlKey");
        uploadDate = i.getStringExtra("dateKey");
        id = i.getStringExtra("idKey");
        num = Integer.valueOf(i.getStringExtra("indexKey"));
        jp = new JsonParse();

        mp = new MediaPlayer();
        try {
            mp.setDataSource(Uri.decode(url));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvUploadDate = (TextView) findViewById(R.id.tvDate);
        tv = (TextView) findViewById(R.id.tv);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        tvTitle.setText(id);
        tvUploadDate.setText(uploadDate);

        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        seekBar.setOnTouchListener(this);

        seekBar.setProgress(0);
        seekBar.setMax(mp.getDuration());

        qwe = new String[100];
        qwe = jp.TagDataGetter(num);

        // tv.setText(qwe[0]);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPause:
                mp.pause();
                break;
            case R.id.btnStop:
                mp.stop();
                mp.reset();
                mp.release();
                mp = new MediaPlayer();
                try {
                    mp.setDataSource(Uri.decode(url));
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPlay:
                mp.start();
                startPlayProgressUpdater();
                break;

        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mp.isPlaying()) {
            SeekBar sb = (SeekBar) v;
            mp.seekTo(sb.getProgress());
        }
        return false;
    }

    public void startPlayProgressUpdater() {
        seekBar.setProgress(mp.getCurrentPosition());

        if (mp.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        } else {
            mp.pause();

            seekBar.setProgress(0);

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
