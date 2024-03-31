package edu.filsrouge.VskinVault;

import android.content.Context;
import android.content.Intent;
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

public class CommandActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);


        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.toString(source.charAt(i)).matches("[\\t\\n$=*+(){}/?!:;,\"<>%-]")) {return "";}
                }
                return null;
            }
        };

        String[] categories = getResources().getStringArray(R.array.category);
        TextView[] titles = new TextView[6];
        TextInputLayout[] textInputLayout = new TextInputLayout[6];
        TextInputEditText[] textInputEditTexts = new TextInputEditText[6];
        titles[0] = findViewById(R.id.centerTitle);
        titles[0].setText(categories[0]);
        titles[0].setTextSize(20);
        for (int i = 1; i <categories.length ; i++) {
            titles[i] = findViewById(getResources().getIdentifier("textView"+i, "id", getPackageName()));
            titles[i].setText(categories[i]);
            textInputLayout[i] = findViewById(getResources().getIdentifier("textInputLayout"+i, "id", getPackageName()));
            textInputEditTexts[i] = findViewById(getResources().getIdentifier("value"+i, "id", getPackageName()));
            textInputEditTexts[i].setFilters(new InputFilter[]{filter});

        }

        TextView button = findViewById(R.id.confirm_button);
        button.setText(R.string.validate_command);

        //search bar
        TextInputLayout searchLayout = findViewById(R.id.searchBarLayout);
        searchLayout.setHintAnimationEnabled(true);
        TextInputEditText search = findViewById(R.id.searchBar);
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
        TextView menu = findViewById(R.id.navTitle);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez une nouvelle Intent pour ouvrir CommandActivity
                Intent intent = new Intent(CommandActivity.this, MainActivity.class);

                // Démarrez l'activité
                startActivity(intent);
            }
        });/**/
    }
}
