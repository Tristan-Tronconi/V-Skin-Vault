package edu.filsrouge.VskinVault;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * La classe CommandActivity représente une activité dans l'application.
 * Elle étend AppCompatActivity, ce qui signifie qu'elle peut utiliser les fonctionnalités de base d'une activité dans Android.
 */
public class CommandActivity extends AppCompatActivity {

    /**
     * Cette méthode est appelée lorsque l'activité est en train de démarrer.
     * Elle définit la vue de contenu, initialise les éléments de l'interface utilisateur et définit les comportements pour certains événements d'interaction.
     *
     * @param savedInstanceState Si l'activité est en train d'être réinitialisée après avoir été précédemment arrêtée, alors ce Bundle contient les données qu'elle a le plus récemment fournies dans onSaveInstanceState(Bundle). Sinon, il est null.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        //NAV

        //cart & profile
        ImageButton buttonprofil = findViewById(R.id.profil);
        ImageButton buttonpanier = findViewById(R.id.panier);
        buttonpanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommandActivity.this, Cart.class);
                startActivity(intent);
            }
        });
        buttonprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommandActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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

        //Return to main menu
        TextView menu = findViewById(R.id.navTitle);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommandActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });/**/


        //CONTENT
        String[] categories = getResources().getStringArray(R.array.command);
        TextView[] titles = new TextView[6];
        TextInputLayout[] textInputLayout = new TextInputLayout[5];
        TextInputEditText[] textInputEditTexts = new TextInputEditText[5];
        titles[0] = findViewById(R.id.centerTitle);
        titles[0].setText(categories[0]);
        titles[0].setTextSize(20);
        for (int i = 1; i <categories.length ; i++) {
            titles[i] = findViewById(getResources().getIdentifier("textView"+i, "id", getPackageName()));
            titles[i].setText(categories[i]);
            textInputLayout[i-1] = findViewById(getResources().getIdentifier("textInputLayout"+i, "id", getPackageName()));
            textInputEditTexts[i-1] = findViewById(getResources().getIdentifier("value"+i, "id", getPackageName()));
            textInputEditTexts[i-1].setFilters(new InputFilter[]{filter});
        }

        TextView button = findViewById(R.id.confirm_button);
        button.setText(R.string.validate_command);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TextInputEditText textInputEditText : textInputEditTexts) {
                    if (textInputEditText.getText().toString().isEmpty()) {
                        Toast.makeText(CommandActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                new AlertDialog.Builder(CommandActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Êtes-vous sûr de vouloir confirmer le payment ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(CommandActivity.this, MainActivity.class));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        });
    }
}
