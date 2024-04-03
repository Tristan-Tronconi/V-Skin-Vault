package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

/**
 * La classe AnimationStart représente l'animation de démarrage de l'application.
 * Elle étend AppCompatActivity, ce qui signifie qu'elle peut utiliser les fonctionnalités de base d'une activité dans Android.
 */
public class AnimationStart extends AppCompatActivity {
    AnimationDrawable animation;

    /**
     * Cette méthode est appelée lorsque l'activité est en train de démarrer.
     * Elle définit la vue de contenu, initialise l'animation et la démarre.
     * Elle définit également un délai égal à la durée de l'animation, après quoi elle démarre l'activité principale et se termine.
     *
     * @param savedInstanceState Si l'activité est en train d'être réinitialisée après avoir été précédemment arrêtée, alors ce Bundle contient les données qu'elle a le plus récemment fournies dans onSaveInstanceState(Bundle). Sinon, il est null.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_start);

        Intent intent = new Intent(AnimationStart.this, MainActivity.class);
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