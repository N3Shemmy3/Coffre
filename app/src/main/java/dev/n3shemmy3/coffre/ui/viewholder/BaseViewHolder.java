package dev.n3shemmy3.coffre.ui.viewholder;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;


public class BaseViewHolder extends RecyclerView.ViewHolder {


    private final View itemView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public static BaseViewHolder create(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    public static void onBindViewHolder(int position) {

    }

    public void setItemListener(@NonNull ItemListener listener) {
        itemView.setOnClickListener(v -> listener.onItemClicked(itemView, null, getLayoutPosition()));
        itemView.setOnLongClickListener(v -> {
            listener.onItemLongClicked(itemView, null, getLayoutPosition());
            return true;
        });
    }


}