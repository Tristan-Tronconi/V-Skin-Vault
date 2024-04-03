package edu.filsrouge.VskinVault;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Classe Cart qui étend AppCompatActivity.
 * Cette classe est responsable de la gestion de l'activité Cart de l'application.
 */
public class Cart extends AppCompatActivity {

    /**
     * Cette méthode est appelée lorsque l'activité démarre.
     * @param savedInstanceState Si l'activité est réinitialisée après avoir été précédemment arrêtée, alors ce Bundle contient les données qu'elle a le plus récemment fournies dans onSaveInstanceState(Bundle). Note : Sinon, il est null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        // Initialisation des boutons
        ImageButton buttonprofil = findViewById(R.id.profil);
        ImageButton buttonpanier = findViewById(R.id.panier);

        // Définition du onClickListener pour buttonpanier pour démarrer l'activité Cart
        buttonpanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, Cart.class);
                startActivity(intent);
            }
        });

        // Définition du onClickListener pour buttonprofil pour démarrer l'activité LoginActivity
        buttonprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, LoginActivity.class);
                startActivity(intent);
            }
        });

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

        // Définition du onClickListener pour le menu pour démarrer MainActivity
        TextView menu = findViewById(R.id.navTitle);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Définition du onClickListener pour valider pour démarrer CommandActivity
        Button valider = findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, CommandActivity.class);
                startActivity(intent);
            }
        });

    }
}