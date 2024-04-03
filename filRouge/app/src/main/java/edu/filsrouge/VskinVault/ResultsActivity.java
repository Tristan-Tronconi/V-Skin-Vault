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

import java.util.ArrayList;
import java.util.Arrays;

public class ResultsActivity extends AppCompatActivity implements Clickable {

    private ArrayList<Product> products = new ArrayList<>();
    @Override
    public void onClicItem(int itemIndex) {
        Log.d(TAG, "Item clicked: " + itemIndex);
        Intent intent = new Intent(ResultsActivity.this, DetailsActivity.class);
        // Use the product ID instead of the item index
        String productId = products.get(itemIndex).getId();
        intent.putExtra("PRODUCT_ID", productId);
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

//        Product p1 = new Product("CID_102_Athena_Commando_M_Raven","Raven","Backpack","Legendary","1","1","A btestststackpack","https://fortnite-api.com/images/cosmetics/br/backpack_abstractmirror/icon.png","icon","smallIcon");
//        Product p2 = new Product("CID_102_Athena_Commando_M_Raven","Raven","Backpack","Legendary","1","1","A backpack","https://fortnite-api.com/images/cosmetics/br/backpack_abstractmirror/icon.png","icon","smallIcon");

//        products.addAll(Arrays.asList(new Product[]{p1, p2}));

        class myTask extends AsyncTask<Void, Void, Product[]> {
            @Override
            protected Product[] doInBackground(Void... voids) {
                return requestHandler.searchCosmeticsByType("backpack");
            }

            @Override
            protected void onPostExecute(Product[] result) {
                if (result != null) {
                    System.out.println("Products: " + result.length);
                    ComponentonlyActivity adapter = new ComponentonlyActivity(ResultsActivity.this, result);
                    listView.setAdapter(adapter);
                } else {
                    System.out.println("No products found");
                }
            }
        }

        myTask task = new myTask();
        task.execute();
        ComponentonlyActivity adapter = new ComponentonlyActivity(ResultsActivity.this, products.toArray(new Product[0]));
        listView.setAdapter(adapter);

        ProductAdapter adapter2 = new ProductAdapter(products, this);
        listView.setAdapter(adapter2);



    }


}