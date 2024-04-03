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
    private Product product;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
