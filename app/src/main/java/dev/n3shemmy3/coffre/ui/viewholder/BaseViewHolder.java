package dev.n3shemmy3.coffre.ui.viewholder;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {


    public View itemView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public abstract void onBindViewHolder(T item);


}