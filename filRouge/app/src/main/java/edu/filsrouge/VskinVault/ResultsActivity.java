package edu.filsrouge.VskinVault;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class ResultsActivity extends AppCompatActivity implements Clickable {
    @Override
    public void onClicItem(int itemIndex) {
        Log.d(TAG, "Item clicked: " + itemIndex);
        Intent intent = new Intent(ResultsActivity.this, DetailsActivity.class);
        intent.putExtra("itemIndex", itemIndex);
        startActivity(intent);
    }

    @Override
    public void onRatingBarChange(int itemIndex, float value) {

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        ListView listView = findViewById(R.id.listView);

        RequestHandler requestHandler = new RequestHandler();

        /*class myTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                Product[] products = requestHandler.searchCosmeticsByType("backpack");

                if (products != null) {
                    System.out.println("Products: " + products.length);

                    ComponentonlyActivity adapter = new ComponentonlyActivity(ResultsActivity.this, products);
                    listView.setAdapter(adapter);
                } else {
                    System.out.println("No products found");
                    System.out.println("Products: " + products.length);
                }

                return null;
            }
        }

        myTask task = new myTask();
        task.execute();*/

        Product p1 = new Product("1","Raven","Backpack","Legendary","1","1","A backpack","https://fortnite-api.com/images/cosmetics/br/backpack_abstractmirror/icon.png","icon","smallIcon");
        Product p2 = new Product("2","Raven","Backpack","Legendary","1","1","A backpack","https://fortnite-api.com/images/cosmetics/br/backpack_abstractmirror/icon.png","icon","smallIcon");

        Product[] products = {p1,p2};
        ComponentonlyActivity adapter = new ComponentonlyActivity(ResultsActivity.this, products);
        listView.setAdapter(adapter);

        ProductAdapter adapter2 = new ProductAdapter(Arrays.asList(products), this);
        listView.setAdapter(adapter2);



    }


}