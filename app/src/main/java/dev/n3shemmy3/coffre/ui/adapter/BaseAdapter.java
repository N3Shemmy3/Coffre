package dev.n3shemmy3.coffre.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.ui.diffutils.BaseItemDiffUtil;
import dev.n3shemmy3.coffre.ui.items.BaseItem;
import dev.n3shemmy3.coffre.ui.viewholder.BaseViewHolder;

public abstract class BaseAdapter<T> extends ListAdapter<T, BaseViewHolder<T>> {
    protected ArrayList<T> items = new ArrayList<>();

    protected BaseAdapter() {
        super(new DiffUtil.ItemCallback<>() {
            @Override
            public boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem) {
                return false;
            }
        });
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {
        holder.onBindViewHolder(getItem(position));
    }

/*
    public void submitList(ArrayList<T> items) {
        this.items = items;
        notifyItemRangeChanged(0, items.size());
    }

    public void add(T item) {
        this.items.add(item);
        notifyItemInserted(this.items.size() - 1);
    }

    public void add(int position, T item) {
        this.items.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(ArrayList<T> items) {
        this.items.addAll(items);
        notifyItemRangeInserted(0, items.size());
    }

    public T getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
    */
}
