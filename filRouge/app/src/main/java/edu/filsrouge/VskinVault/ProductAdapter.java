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

public class ProductAdapter extends BaseAdapter {
    private final String TAG = "fred " + getClass().getSimpleName();
    private final List<Product> products;
    private final LayoutInflater mInflater;
    private final Clickable callBackActivity;

    public ProductAdapter(List<Product> products, Clickable callBackActivity) {
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

        TextView name = layoutItem.findViewById(R.id.name);
        TextView description = layoutItem.findViewById(R.id.desc);
        TextView price = layoutItem.findViewById(R.id.price);
        ImageView picture = layoutItem.findViewById(R.id.image);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);

        name.setText(products.get(position).getName());
        description.setText(products.get(position).getDescription());
        price.setText("price : XXXX €");
        picture.setImageResource(R.drawable.ic_launcher_background);

        Random random = new Random();
        ratingBar.setRating(random.nextInt(5) + 1);

        ratingBar.setOnRatingBarChangeListener((ratingBar1, value, b) -> {
            Log.d(TAG, " seekbar change to " + value);
            callBackActivity.onRatingBarChange(position,value);
        });

        layoutItem.setOnClickListener( click -> callBackActivity.onClicItem(position));

        return layoutItem;
    }
}