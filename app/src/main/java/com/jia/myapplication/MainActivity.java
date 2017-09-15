package com.jia.myapplication;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.jia.jsplayer.listener.OnVideoControlListener;
import com.jia.jsplayer.utils.DisplayUtils;
import com.jia.jsplayer.video.JsPlayer;

public class MainActivity extends AppCompatActivity {

    // 播放器
    private JsPlayer player;

    private String path = "http://baobab.wdjcdn.com/1455782903700jy.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (JsPlayer) findViewById(R.id.player);

        player.setOnVideoControlListener(new OnVideoControlListener() {
            @Override
            public void onStartPlay() {
                player.startPlay();
            }

            @Override
            public void onBack() {

            }

            @Override
            public void onFullScreen() {
                DisplayUtils.toggleScreenOrientation(MainActivity.this);
            }

            @Override
            public void onRetry(int errorStatus) {

            }
        });

        player.setPath(new VideoInfo("艺术人生", path));


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public void onBackPressed() {
        if (!DisplayUtils.isPortrait(this)) {
            if (!player.isLock()) {
                DisplayUtils.toggleScreenOrientation(this);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.onDestroy();
    }
}
