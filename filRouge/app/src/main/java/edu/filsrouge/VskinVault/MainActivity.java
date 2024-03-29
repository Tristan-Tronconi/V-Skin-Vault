package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_command);
        setContentView(R.layout.activity_main);

        String[] categories = getResources().getStringArray(R.array.category);
        ///nav connexion
        // nav pannier
        ImageView[] images = new ImageView[6];
        LinearLayout[] layouts = new LinearLayout[6];
        TextView[] titles = new TextView[6];

        for(int i = 0; i < categories.length; i++) {
            layouts[i]= findViewById(getResources().getIdentifier("navLayout"+i, "id", getPackageName()));
            //images-categories[i] = (ImageView) layouts[i].getChildAt(0);
            //images[i].setImageResource();
            titles[i] = (TextView) layouts[i].getChildAt(1);
            titles[i].setText(categories[i]);
        }/**/


        ImageButton button = findViewById(R.id.profil);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez une nouvelle Intent pour ouvrir CommandActivity
                Intent intent = new Intent(MainActivity.this, CommandActivity.class);

                // Démarrez l'activité
                startActivity(intent);
            }
        });/**/
    }
}