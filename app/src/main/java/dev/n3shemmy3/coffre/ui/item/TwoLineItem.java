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

import dev.n3shemmy3.coffre.R;

public class TwoLineItem extends OneLineItem {
    public final TextView itemSubTitle;
    public TwoLineItem(@NonNull View itemView) {
        super(itemView);
        this.itemSubTitle = itemView.findViewById(R.id.itemSubTitle);
    }
    @NonNull
    public static TwoLineItem create(@NonNull ViewGroup parent) {
        return new TwoLineItem(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_two_line, parent, false));
    }
}
