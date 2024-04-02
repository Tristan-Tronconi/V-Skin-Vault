package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Panier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        Intent intent = new Intent(Panier.this, CommandActivity.class);
        Button buttonvalider = findViewById(R.id.valider);
        buttonvalider.setOnClickListener(v -> {
            startActivity(intent);
        });

    }
}