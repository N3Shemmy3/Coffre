package dev.n3shemmy3.coffre.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.viewholder.AccountItemViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.BaseViewHolder;

public class AccountsViewPagerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private ArrayList<String> items;
    private ItemListener itemListener;


    public AccountsViewPagerAdapter(@NonNull Context context, @NonNull ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    public void setItemListener(@NonNull ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        holder = AccountItemViewHolder.create(parent, R.layout.item_account_card);
        holder.setItemListener(itemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
