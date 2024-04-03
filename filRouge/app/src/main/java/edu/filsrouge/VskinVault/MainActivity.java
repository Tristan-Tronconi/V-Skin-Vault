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

/**
 * Classe MainActivity qui étend AppCompatActivity.
 * Cette classe est responsable de la gestion de l'activité principale de l'application.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Cette méthode est appelée lorsque l'activité démarre.
     * @param savedInstanceState Si l'activité est réinitialisée après avoir été précédemment arrêtée, alors ce Bundle contient les données qu'elle a le plus récemment fournies dans onSaveInstanceState(Bundle). Note : Sinon, il est null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des boutons et des animations
        ImageButton buttonprofil = findViewById(R.id.profil);
        ImageButton buttonpanier = findViewById(R.id.panier);
        final Animation disapear = AnimationUtils.loadAnimation(this, R.anim.disapear);
        final Animation rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);

        // Définition du onClickListener pour buttonpanier pour démarrer l'activité Cart
        buttonpanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                startActivity(intent);
            }
        });

        // Définition du onClickListener pour buttonprofil pour démarrer l'activité LoginActivity
        buttonprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Définition du onClickListener pour chaque navLayout pour démarrer ComponentActivity avec la catégorie en extra
        for (int i =0; i < 6; i++) {
            final int finalI = i;
            findViewById(getResources().getIdentifier("navLayout" + i, "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(disapear);
                    v.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, ComponentActivity.class);
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

        // Initialisation des catégories, images, layouts et titres
        String[] categories = getResources().getStringArray(R.array.category);
        ImageView[] images = new ImageView[6];
        LinearLayout[] layouts = new LinearLayout[6];
        TextView[] titles = new TextView[6];

        // Définition du texte de chaque titre à la catégorie correspondante
        for(int i = 0; i < categories.length; i++) {
            layouts[i]= findViewById(getResources().getIdentifier("navLayout"+i, "id", getPackageName()));
            titles[i] = (TextView) layouts[i].getChildAt(1);
            titles[i].setText(categories[i]);
        }

        // Initialisation de la barre de recherche et définition du filtre d'entrée
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

        // Définition du onTouchListener pour la vue de contenu pour effacer le focus de la barre de recherche
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

        // Définition du onClickListener pour chaque navLayout pour démarrer ComponentActivity avec la catégorie en extra
        // Définition de l'image et du texte pour chaque navLayout
        for (int i =0; i < 6; i++) {
            final int finalI = i;
            findViewById(getResources().getIdentifier("navLayout" + i, "id", getPackageName())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ComponentActivity.class);
                    intent.putExtra("category", finalI);
                    startActivity(intent);
                }
            });
            layouts[i]= findViewById(getResources().getIdentifier("navLayout"+i, "id", getPackageName()));
            images[i] = (ImageView) layouts[i].getChildAt(0);
            images[i].setImageResource(getResources().getIdentifier("cath_"+i, "drawable", getPackageName()));
            images[i].setBackground(null);
            titles[i] = (TextView) layouts[i].getChildAt(1);
            titles[i].setText(categories[i]);
        }

        // Planification d'une demande de travail périodique pour les notifications
        PeriodicWorkRequest notificationRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES)
                .build();

        WorkManager.getInstance().enqueue(notificationRequest);
    }
}