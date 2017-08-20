package com.example.jevin.styleomega.Activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jevin.styleomega.Model.Item;
import com.example.jevin.styleomega.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Jevin on 8/12/2017.
 */


public class ItemAdapter extends ArrayAdapter<Item>{

    private List<Item> items;

    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_row, parent, false);
        }

        Item item = items.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.itemName);
        nameText.setText(item.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(item.getPrice());
        TextView priceText = (TextView) convertView.findViewById(R.id.itemPrice);
        priceText.setText(price);

        ImageView itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
        itemImage.setImageResource(R.drawable.mens_shirt_orange);


        /*ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        iv.setImageBitmap(bitmap);*/

        return convertView;
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(productId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}