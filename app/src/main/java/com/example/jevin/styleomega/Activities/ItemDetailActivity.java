package com.example.jevin.styleomega.Activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jevin.styleomega.Model.Item;
import com.example.jevin.styleomega.Others.ItemAdapter;
import com.example.jevin.styleomega.R;

import java.text.NumberFormat;

public class ItemDetailActivity extends BaseActivity {

    TextView nameTxt;
    TextView pricetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_item_detail, contentFrameLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);

        int i = getIntent().getIntExtra(ItemAdapter.ITEM_ID,0);
        Item item = ItemAdapter.itemList.get(i);


        //INITIALIZE VIEWS
        nameTxt= (TextView) findViewById(R.id.nameTxtDetail);
        pricetxt= (TextView) findViewById(R.id.price);

        //BIND DATA
        nameTxt.setText(item.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(item.getPrice());
        pricetxt.setText(price);


    }
}
