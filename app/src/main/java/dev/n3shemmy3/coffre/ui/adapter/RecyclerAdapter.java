package dev.n3shemmy3.coffre.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.items.AccountItem;
import dev.n3shemmy3.coffre.ui.items.BaseItem;
import dev.n3shemmy3.coffre.ui.items.SectionItem;
import dev.n3shemmy3.coffre.ui.viewholder.BaseViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.AccountPagerViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.SectionViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.SingleLineItemViewHolder;


public class RecyclerAdapter extends androidx.recyclerview.widget.ListAdapter<BaseItem, BaseViewHolder<BaseItem>> {

    public RecyclerAdapter() {
        super(new ItemDC());
    }

    public enum ViewTypes {
        SINGLE_LINE, DOUBLE_LINE, RECYCLER_SECTION, RECYCLER_PAGER, LOADER
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof SectionItem) return ViewTypes.RECYCLER_SECTION.ordinal();
        if (getItem(position) instanceof AccountItem) return ViewTypes.RECYCLER_PAGER.ordinal();
        if (getItem(position) != null) return ViewTypes.SINGLE_LINE.ordinal();
        return ViewTypes.SINGLE_LINE.ordinal();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ViewTypes.RECYCLER_SECTION.ordinal())
            return SectionViewHolder.create(parent, R.layout.recycler_section);
        if (viewType == ViewTypes.RECYCLER_PAGER.ordinal())
            return AccountPagerViewHolder.create(parent, R.layout.item_pager_account);

        return SingleLineItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BaseItem> holder, int position) {
        holder.onBindViewHolder(getItem(position));
    }


    private static class ItemDC extends DiffUtil.ItemCallback<BaseItem> {
        @Override
        public boolean areItemsTheSame(@NonNull BaseItem oldItem, @NonNull BaseItem newItem) {

            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull BaseItem oldItem, @NonNull BaseItem newItem) {
            return false;
        }
    }
}
