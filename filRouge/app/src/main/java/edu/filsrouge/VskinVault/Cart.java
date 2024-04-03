package edu.filsrouge.VskinVault;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe Cart qui étend AppCompatActivity.
 * Cette classe est responsable de la gestion de l'activité Cart de l'application.
 */


public class Cart extends AppCompatActivity implements Clickable {

    private final String TAG = "fred " + getClass().getSimpleName();
    private  PanierAdapter adapter;

    List<Product> products = new ArrayList<>();

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
        });/**/

        //Code ListView

        ListView listview = findViewById(R.id.listView);
        Product fakeProduct = new Product(
                "fakeId", // id
                "fakeName", // name
                "fakeType", // type
                "fakeRarity", // rarity
                "fakeChapter", // chapter
                "fakeSeason", // season
                "fakeDescription", // description
                "fakeImage", // image
                "fakeIcon", // icon
                "fakeSmallIcon" // smallIcon
        );

        products.add(fakeProduct);
        products.add(fakeProduct);
        products.add(fakeProduct);
        adapter = new PanierAdapter(products, this);
        listview.setAdapter(adapter);







        Button valider = findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, CommandActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onClicItem(int itemIndex) {
        //envoie à la page du produit
    }

    private int findIndexInList(int index) {
        Product productToFind = products.get(index);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(productToFind)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onRatingBarChange(int itemIndex, float value) {
        //pour changer valeur rating bar
        //products.get(findIndexInList(itemIndex)).setValue(value);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
