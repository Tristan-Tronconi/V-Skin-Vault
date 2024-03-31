package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class animationStart extends AppCompatActivity {
    AnimationDrawable animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_start);

        Intent intent = new Intent(animationStart.this, MainActivity.class);
        ImageView anim = findViewById(R.id.animation);
        anim.setBackgroundResource(R.drawable.dance);
        animation = (AnimationDrawable) anim.getBackground();
        animation.start();

        int duration = 0;
        for (int i = 0; i < animation.getNumberOfFrames(); i++) {
            duration += animation.getDuration(i);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
                finish();
            }
        }, duration);

    }
}