package dev.n3shemmy3.coffre.ui.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceViewHolder;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

import dev.n3shemmy3.coffre.R;

@SuppressLint("RestrictedApi")
public class PreferencesAdapter extends PreferenceGroupAdapter {
    public PreferencesAdapter(PreferenceGroup preferenceGroup) {
        super(preferenceGroup);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public Preference getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (holder.itemView instanceof MaterialCardView) {
            MaterialCardView cardView = (MaterialCardView) holder.itemView;
            float radius = holder.itemView.getResources().getDimension(R.dimen.material_card_corner_radius);
            float topRadius = position == 0 ? radius : 0;
            float bottomRadius = position == getItemCount() - 1 ? radius : 0;
            cardView.setShapeAppearanceModel(
                    cardView.getShapeAppearanceModel()
                            .toBuilder()
                            .setTopLeftCorner(CornerFamily.ROUNDED, topRadius)
                            .setTopRightCorner(CornerFamily.ROUNDED, topRadius)
                            .setBottomRightCorner(CornerFamily.ROUNDED, bottomRadius)
                            .setBottomLeftCorner(CornerFamily.ROUNDED, bottomRadius)
                            .build());
        }

    }


}
