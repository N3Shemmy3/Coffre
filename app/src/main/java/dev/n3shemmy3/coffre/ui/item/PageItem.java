package dev.n3shemmy3.coffre.ui.item;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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