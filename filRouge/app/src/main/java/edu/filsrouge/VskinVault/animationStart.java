package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class animationStart extends AppCompatActivity {
    AnimationDrawable animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_start);

        ImageView anim = findViewById(R.id.animation);
        anim.setBackgroundResource(R.drawable.dance);
        animation = (AnimationDrawable) anim.getBackground();
        animation.start();

    }
}