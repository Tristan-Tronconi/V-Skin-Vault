package edu.filsrouge.VskinVault;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.componentonly_layout, parent, false);
        }

        Product product = getItem(position);

        TextView name = convertView.findViewById(R.id.nom);
        ImageView image = convertView.findViewById(R.id.imageView);
        name.setText(product.getName());

        System.out.println("image " + image);
        // Check if the ImageView and the image URL are not null before loading the image with Picasso
        if (image != null && product.getIcon() != null) {
            Picasso.get().load(product.getIcon()).into(image);
        }


        // Update other views here if necessary

        return convertView;
    }
}
