package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import okhttp3.Callback;

public class DetailsActivity extends AppCompatActivity{
    private RequestHandler requestHandler = new RequestHandler();
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String productId = getIntent().getStringExtra("PRODUCT_ID");

        // Utilisez productId pour faire une nouvelle requête avec RequestHandler
        new GetCosmeticInfoTask().execute(productId);
    }

    private class GetCosmeticInfoTask extends AsyncTask<String, Void, Product> {
        @Override
        protected Product doInBackground(String... params) {
            String productId = params[0];
            return requestHandler.getCosmeticInfo(productId);
        }

        @Override
        protected void onPostExecute(Product result) {
            product = result;
            // Update your UI here based on the 'product' data
            TextView name = findViewById(R.id.title);
            ImageView image = findViewById(R.id.imageView);
            TextView price = findViewById(R.id.price);
            TextView description = findViewById(R.id.desc);

            if (product != null) {
                name.setText(product.getName());
                price.setText(product.getPrice());
                description.setText(product.getDescription());
                Picasso.get().load(product.getImage()).into(image);
            }
        }
    }
}