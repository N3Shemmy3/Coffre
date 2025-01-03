package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.adapter.PreferencesAdapter;
import dev.n3shemmy3.coffre.ui.decorator.NoDividerItemDecoration;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;

public abstract class BasePreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDivider(new ColorDrawable(Color.TRANSPARENT));
        setDividerHeight(0);
        RecyclerView recyclerView = getListView();
        if (recyclerView != null) {
            int padding = getResources().getDimensionPixelSize(R.dimen.activity_padding_vertical);
            recyclerView.setClipToPadding(false);
            recyclerView.addItemDecoration(new NoDividerItemDecoration());
            recyclerView.setPadding(recyclerView.getPaddingLeft(), padding / 2, recyclerView.getPaddingRight(), padding);
            InsetUtils.applyContentInsets(recyclerView);
        }
    }

    // Add this with modified logic
    @NonNull
    @Override
    protected RecyclerView.Adapter<PreferenceViewHolder> onCreateAdapter(@NonNull PreferenceScreen preferenceScreen) {
        PreferencesAdapter adapter = new PreferencesAdapter(preferenceScreen);
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        return adapter;
    }
}
