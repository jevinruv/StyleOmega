package com.example.jevin.styleomega.Others;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.jevin.styleomega.Activities.ItemDetailActivity;
import com.example.jevin.styleomega.Model.Item;
import com.example.jevin.styleomega.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    Context c;
    public static List<Item> itemList;
    public static final String ITEM_ID = "ITEM_ID";

    public ItemAdapter(Context c, List<Item> itemList) {
        this.c = c;
        this.itemList = itemList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, final int position) {
        final String name=itemList.get(position).getName();
        final String id=itemList.get(position).getId();

        //BIND DATA
        holder.nameTxt.setText(name);
        holder.id.setText(id);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                Intent intent =new Intent(c, ItemDetailActivity.class);
                intent.putExtra(ITEM_ID,id);

                c.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



}
