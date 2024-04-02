package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Intent intent = new Intent(LoginActivity.this, InscriptionActivity.class);
        Button registerButton = findViewById(R.id.registerbutton);
        registerButton.setOnClickListener(v -> {
            startActivity(intent);

        });

    }
}
