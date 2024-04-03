package edu.filsrouge.VskinVault;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

class ComponentonlyActivity extends ArrayAdapter<Product> {
    ComponentonlyActivity(Context context, Product[] products) {
        super(context, 0, products);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.componentonly_layout, parent, false);
        }

        Product product = getItem(position);

        TextView name = convertView.findViewById(R.id.nom);
        ImageView image = convertView.findViewById(R.id.imageView);
        TextView desc = convertView.findViewById(R.id.categorie);
        TextView price = convertView.findViewById(R.id.prix);

        assert product != null;
        price.setText(product.getPrice() + " €");

        // Check if the TextViews and the ImageView are not null before setting their values
        if (name != null && desc != null && image != null) {
            desc.setText(product.getType()+ " - C" + product.getChapter() + "S" + product.getSeason() + " - " + product.getRarity());
            name.setText(product.getName());

            if (product.getIcon() != null) {
                Picasso.get().load(product.getIcon()).into(image);
            }
        }

        // Update other views here if necessary

        return convertView;
    }
}
