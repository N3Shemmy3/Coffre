package dev.n3shemmy3.coffre.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import dev.n3shemmy3.coffre.ui.diffutils.BaseItemDiffUtil;
import dev.n3shemmy3.coffre.ui.items.BaseItem;
import dev.n3shemmy3.coffre.ui.viewholder.AccountPagerViewHolder;

public class AccountCardAdapter extends ListAdapter<BaseItem, AccountPagerViewHolder> {
    protected AccountCardAdapter() {
        super(new BaseItemDiffUtil());
    }

    @NonNull
    @Override
    public AccountPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AccountPagerViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountPagerViewHolder holder, int position) {

    }
}
