package com.kuaimu.android.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.kuaimu.android.app.R;

public class PreviewVideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private String videoPath;
    private String coverPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_video);

        videoPath = getIntent().getStringExtra("videoPath");
        coverPath = getIntent().getStringExtra("coverPath");

        videoView = findViewById(R.id.video_view);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                            videoView.setBackgroundColor(Color.TRANSPARENT);
                        return true;
                    }
                });
                mediaPlayer.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        videoView.setVideoPath(videoPath);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviewVideoActivity.this, ReleaseWorkActivity.class);
                intent.putExtra("videoPath", videoPath);
                intent.putExtra("coverPath", coverPath);
                startActivity(intent);
                finish();
            }
        });
    }
}