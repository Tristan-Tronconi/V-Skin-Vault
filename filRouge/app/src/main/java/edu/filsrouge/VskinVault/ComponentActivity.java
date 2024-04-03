package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ComponentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Product selectedProduct = (Product) parent.getItemAtPosition(position);
                System.out.println("Selected product: " + selectedProduct);
                System.out.println("Selected product ID: " + selectedProduct.getId()); // Ajout du System.out.println()
                Intent intent = new Intent(ComponentActivity.this, DetailsActivity.class);
                intent.putExtra("PRODUCT_ID", selectedProduct.getId());
                startActivity(intent);/*
                 */
                System.out.println("Selected product: " + parent.getItemAtPosition(position));
                System.out.println("Selected class " + parent.getItemAtPosition(position).getClass());
            }
        });


    }
}
