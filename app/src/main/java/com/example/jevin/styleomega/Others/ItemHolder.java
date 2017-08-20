package com.example.jevin.styleomega.Others;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jevin.styleomega.R;


public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt;
    TextView id;
    ItemClickListener itemClickListener;

    public ItemHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.itemName);
        id= (TextView) itemView.findViewById(R.id.itemPrice);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
