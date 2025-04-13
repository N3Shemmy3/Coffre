package dev.n3shemmy3.coffre.ui.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;

public class PageItem extends RecyclerView.ViewHolder {

    public TextView emoticon;
    public TextView title;
    public TextView summary;

    public PageItem(@NonNull View itemView) {
        super(itemView);
        /*
        emoticon = itemView.findViewById(R.id.emoticon);
        title = itemView.findViewById(R.id.title);
        summary = itemView.findViewById(R.id.summary);

         */

    }

    @NonNull
    public static PageItem create(@NonNull ViewGroup parent) {
        return new PageItem(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.screen_start, parent, false));
    }
}