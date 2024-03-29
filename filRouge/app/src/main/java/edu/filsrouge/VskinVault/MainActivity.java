package edu.filsrouge.VskinVault;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_command);
        setContentView(R.layout.activity_main);

        String[] categories = getResources().getStringArray(R.array.category);
        LinearLayout[] layouts = new LinearLayout[6];
        TextView[] titles = new TextView[6];

        for(int i = 0; i < categories.length; i++) {
            layouts[i] = findViewById(getResources().getIdentifier("navLayout" + i, "id", getPackageName()));

            if (titles[i] != null) {
                titles[i].setText(categories[i]);
            } else {
                Log.e("MainActivity", "TextView with id 'navLayout" + i + "' is not found.");
            }
        }

    }
}