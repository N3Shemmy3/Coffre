package dev.n3shemmy3.coffre.ui.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.items.BaseRecyclerItem;
import dev.n3shemmy3.coffre.ui.items.RecyclerItem;
import dev.n3shemmy3.coffre.ui.items.SectionItem;

public class SectionViewHolder extends RecyclerViewHolder {

    public TextView itemSectionHeader;
    public Button itemSectionButton;


    public SectionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemSectionHeader = itemView.findViewById(R.id.itemSectionHeader);
        this.itemSectionButton = itemView.findViewById(R.id.itemSectionButton);
    }

    @Override
    public void onBindViewHolder(RecyclerItem item) {
        super.onBindViewHolder(item);
        SectionItem sectionItem = (SectionItem) item;
        this.itemSectionHeader.setText(sectionItem.getTitle());
        this.itemSectionButton.setText(sectionItem.getActionText());

    }

    public static SectionViewHolder create(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        return new SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }
}
