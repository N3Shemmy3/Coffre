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
import dev.n3shemmy3.coffre.ui.items.BaseItem;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {


    public View itemView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public abstract void onBindViewHolder(T item);


}