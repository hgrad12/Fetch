package com.example.fetch.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetch.R;
import com.example.fetch.model.Item;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder> {

    private final List<Item> listOfItems;
    private final Context context;

    public ItemRecyclerAdapter(List<Item> items, Context context) {
        listOfItems = items;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ItemRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemRecyclerAdapter.ViewHolder holder, int position) {
        Item item = listOfItems.get(position);
        holder.id.setText("Id: " + item.getId());
        holder.listId.setText("List Id: " + item.getListId());
        holder.name.setText("Name: " + item.getName());
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, listId, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.item_id);
            listId = itemView.findViewById(R.id.item_listId);
            name = itemView.findViewById(R.id.item_name);
        }
    }
}
