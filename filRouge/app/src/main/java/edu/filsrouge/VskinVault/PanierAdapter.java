package edu.filsrouge.VskinVault;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * Modify by Fred on 16/02/2024.
 */

public class PanierAdapter extends BaseAdapter {
    private final String TAG = "fred " + getClass().getSimpleName();

    private List<Product> products;
    private LayoutInflater mInflater;
    private Clickable callBackActivity;


    public PanierAdapter(List<Product> products, Clickable callBackActivity) {
        this.products = products;
        this.callBackActivity = callBackActivity;
        mInflater = LayoutInflater.from(callBackActivity.getContext());
    }

    public int getCount() {
        return products.size();
    }
    public Object getItem(int position) {
        return products.get(position);
    }
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;

        //(1) : Réutilisation des layouts impossible (mémorisation des valeurs du ratingBar)
        //layoutItem = (ConstraintLayout) (convertView == null ? mInflater.inflate(R.layout.character_layout, parent, false) : convertView);
        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.panier_product_layout, parent, false);


        //(2) : Récupération des éléments
        ImageView picture = layoutItem.findViewById(R.id.image);
        TextView name = layoutItem.findViewById(R.id.name);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);
        TextView description = layoutItem.findViewById(R.id.desc);
        TextView price = layoutItem.findViewById(R.id.price);





        //(3) : Renseignement des valeurs
        name.setText(products.get(position).getName());
        description.setText(products.get(position).getDescription());
        //price.setText(products.get(position).getPrice());
        price.setText("price : XXXX €");
        //picture.setImageResource(products.get(position).getIcon());
        picture.setImageResource(R.drawable.ic_launcher_background);

        Random random = new Random();
        ratingBar.setRating(random.nextInt(5) + 1);

        ratingBar.setOnRatingBarChangeListener((ratingBar1, value, b) -> {
            Log.d(TAG, " seekbar change to " + value);
            callBackActivity.onRatingBarChange(position,value);
        });

        layoutItem.setOnClickListener( click -> callBackActivity.onClicItem(position));
        //On retourne l'item créé.
        return layoutItem;
    }


}




