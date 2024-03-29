package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
            images[i] = (ImageView) layouts[i].getChildAt(0);
            images[i].setImageResource(getResources().getIdentifier("cath_"+i, "drawable", getPackageName()));
            images[i].setBackground(null);
            titles[i] = (TextView) layouts[i].getChildAt(1);
            titles[i].setText(categories[i]);
        }/**/

        //search bar
        TextInputLayout searchLayout = findViewById(R.id.searchBarLayout);
        searchLayout.setHintAnimationEnabled(true);
        TextInputEditText search = findViewById(R.id.searchBar);
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.toString(source.charAt(i)).matches("[\\t\\n$=*+(){}/?!:;,\"<>%-]")) {
                        return "";
                    }
                }
                return null;
            }

        };
        search.setFilters(new InputFilter[]{filter});
        //quitter la barre de recherche
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!(v instanceof TextInputEditText)) {
                    search.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return false;
            }
        });




        //retour au menu principal
        ImageButton button = findViewById(R.id.profil);
        int imageResource = getResources().getIdentifier("profil", "drawable", getPackageName());
        if (imageResource != 0) {
            button.setScaleType(ImageView.ScaleType.FIT_XY);
            button.setImageResource(getResources().getIdentifier("profil", "drawable", getPackageName()));
            button.setBackground(null);
        }
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