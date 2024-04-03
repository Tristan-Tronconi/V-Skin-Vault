package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


public class MainActivity extends AppCompatActivity {
    private RequestHandler requestHandler = new RequestHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_command);
        setContentView(R.layout.activity_main);
        ImageButton buttonprofil = findViewById(R.id.profil);
        ImageButton buttonpanier = findViewById(R.id.panier);
        final Animation disapear = AnimationUtils.loadAnimation(this, R.anim.disapear);
        final Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        //ProgressBar loadingSpinner = findViewById(R.id.loading_spinner);

        buttonpanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Panier.class);
                startActivity(intent);
            }
        });
        buttonprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        for (int i =0; i < 6; i++) {
            final int finalI = i;
            findViewById(getResources().getIdentifier("navLayout" + i, "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(disapear);
                    v.setVisibility(View.INVISIBLE);
                    //loadingSpinner.startAnimation(rotateAnim);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                            intent.putExtra("category", finalI);
                            startActivity(intent);
                        }
                    }, 500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            v.setVisibility(View.VISIBLE);
                        }

                    }, 1000);
                }

            });
        }

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
        //exit search bar
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

        for (int i =0; i < 6; i++) {
            final int finalI = i;
            //onclick
            findViewById(getResources().getIdentifier("navLayout" + i, "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                    intent.putExtra("category", finalI);
                    startActivity(intent);
                }
            });
            //affichage
            layouts[i]= findViewById(getResources().getIdentifier("navLayout"+i, "id", getPackageName()));
            images[i] = (ImageView) layouts[i].getChildAt(0);
            images[i].setImageResource(getResources().getIdentifier("cath_"+i, "drawable", getPackageName()));
            images[i].setBackground(null);
            titles[i] = (TextView) layouts[i].getChildAt(1);
            titles[i].setText(categories[i]);
        }

        //Notification
        PeriodicWorkRequest notificationRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES)
                .build();

        WorkManager.getInstance().enqueue(notificationRequest);



    }
}