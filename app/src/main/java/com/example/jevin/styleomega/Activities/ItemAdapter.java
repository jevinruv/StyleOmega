package com.example.jevin.styleomega.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jevin.styleomega.R;

/**
 * Created by Jevin on 8/12/2017.
 */

public class ItemAdapter extends ArrayAdapter<String> {

    public ItemAdapter(Context context, String[] resource) {
        super(context, R.layout.item_row, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.item_row,parent,false);

        String singleItem = getItem(position);
        TextView itemName = (TextView) view.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) view.findViewById(R.id.itemPrice);
        ImageView itemImage = (ImageView) view.findViewById(R.id.itemImage);

        itemName.setText(singleItem);
        itemImage.setImageResource(R.drawable.mens_shirt_orange);

        return view;
    }
}
