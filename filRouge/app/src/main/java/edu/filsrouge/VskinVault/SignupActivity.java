package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        Button connexionbutton = findViewById(R.id.connexionbutton);

        connexionbutton.setOnClickListener(v -> {
            startActivity(intent);
        });

        //Return to main menu
        TextView menu = findViewById(R.id.navTitle);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });/**/
    }
}
