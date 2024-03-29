package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CommandActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        String[] categories = getResources().getStringArray(R.array.category);
        TextView[] titles = new TextView[6];
        titles[0] = findViewById(R.id.centerTitle);
        titles[0].setText(categories[0]);
        titles[0].setTextSize(20);
        for (int i = 1; i <categories.length ; i++) {
            titles[i] = findViewById(getResources().getIdentifier("textView"+i, "id", getPackageName()));
            titles[i].setText(categories[i]);
        }

        TextView button = findViewById(R.id.confirm_button);
        button.setText(R.string.validate_command);


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
