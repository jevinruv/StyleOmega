package com.example.jevin.styleomega.Activities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jevin.styleomega.Model.Item;
import com.example.jevin.styleomega.R;

import java.util.List;


public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ItemViewHolder> {

    private List<Item> listItems;

    public ItemsRecyclerAdapter(List<Item> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textViewName.setText(listItems.get(position).getName());
        holder.textViewid.setText(listItems.get(position).getId());
    }

    @Override
    public int getItemCount() {
        Log.v(ItemsRecyclerAdapter.class.getSimpleName(),""+listItems.size());
        return listItems.size();
    }


    /**
     * ViewHolder class
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewid;

        public ItemViewHolder(View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.itemName);
            textViewid = (TextView) view.findViewById(R.id.itemPrice);
        }
    }


}
