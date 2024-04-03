package edu.filsrouge.VskinVault;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.Callback;

public class DetailsActivity extends AppCompatActivity{
    private RequestHandler requestHandler = new RequestHandler();
    private Product[] product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String productId = getIntent().getStringExtra("PRODUCT_ID");

        // Utilisez productId pour faire une nouvelle requête avec RequestHandler
        RequestHandler requestHandler = new RequestHandler();
        product = requestHandler.getCosmeticInfo(productId);
    }
}
