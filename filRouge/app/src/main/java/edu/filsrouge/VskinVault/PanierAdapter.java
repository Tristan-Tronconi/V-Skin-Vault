package edu.filsrouge.VskinVault;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

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

        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.panier_product_layout, parent, false);

        // Récupération des éléments
        ImageView picture = layoutItem.findViewById(R.id.image);
        TextView name = layoutItem.findViewById(R.id.name);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);
        TextView description = layoutItem.findViewById(R.id.desc);
        TextView price = layoutItem.findViewById(R.id.price);

        // Renseignement des valeurs
        name.setText(products.get(position).getName());
        description.setText(products.get(position).getDescription());
        price.setText("price : XXXX €");
        picture.setImageResource(R.drawable.ic_launcher_background);
        ratingBar.setRating(new Random().nextInt(5) + 1);
        //ratingBar.setRating(products.get(position).getRating());

        ratingBar.setOnRatingBarChangeListener((ratingBar1, value, b) -> {
            Log.d(TAG, " seekbar change to " + value);
            callBackActivity.onRatingBarChange(position,value);
        });

        layoutItem.setOnClickListener( click -> callBackActivity.onClicItem(position));
        // Création de l'animation
        ObjectAnimator animator = ObjectAnimator.ofFloat(layoutItem, "translationY", 3000f, 0f);
        animator.setDuration(1000); // Durée de l'animation en millisecondes
        animator.setStartDelay(position * 200);



        ListView listView = (ListView) parent;
        listView.setDivider(null);
        layoutItem.setVisibility(View.INVISIBLE);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                // Désactiver le divider
                layoutItem.setVisibility(View.VISIBLE);
                listView.setDivider(null);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // Réactiver le divider
                listView.setDivider(ContextCompat.getDrawable(callBackActivity.getContext(), android.R.drawable.divider_horizontal_dark));
            }
        });
        animator.start();

        // On retourne l'item créé.
        return layoutItem;
    }

}




