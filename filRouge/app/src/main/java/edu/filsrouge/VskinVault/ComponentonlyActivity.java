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
        name.setText(product.getName());

        // Mettez à jour d'autres vues ici si nécessaire

        return convertView;
    }
}
