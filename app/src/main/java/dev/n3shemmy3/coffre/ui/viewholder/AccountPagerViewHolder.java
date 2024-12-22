package dev.n3shemmy3.coffre.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;

public class AccountPagerViewHolder extends ItemViewHolder {
    public AccountPagerViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public static AccountPagerViewHolder create(@NonNull ViewGroup parent) {
        return new AccountPagerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager_account, parent, false));
    }

}
