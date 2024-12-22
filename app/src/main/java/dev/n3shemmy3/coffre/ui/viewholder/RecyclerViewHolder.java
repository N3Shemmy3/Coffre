package dev.n3shemmy3.coffre.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.adapter.ItemAdapter;
import dev.n3shemmy3.coffre.ui.items.BaseItem;
import dev.n3shemmy3.coffre.ui.items.RecyclerItem;

public class RecyclerViewHolder extends BaseViewHolder<RecyclerItem> {


    public RecyclerView itemRecycler;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemRecycler = itemView.findViewById(R.id.itemRecycler);
    }

    @Override
    public void onBindViewHolder(RecyclerItem item) {
        this.itemRecycler.setLayoutManager(new LinearLayoutManager(this.itemRecycler.getContext()));
        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.submitList(item.getItems());
        this.itemRecycler.setAdapter(itemAdapter);
    }
    public static RecyclerViewHolder create(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

}
