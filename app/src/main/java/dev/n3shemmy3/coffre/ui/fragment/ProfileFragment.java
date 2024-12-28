package dev.n3shemmy3.coffre.ui.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;

public class ProfileFragment extends BaseFragment {

    private AppBarLayout topAppBar;
    private MaterialToolbar topToolBar;
    private FloatingActionButton fab;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(root, savedInstanceState);
        if (getArguments() != null) {
            String transitionName = getArguments().getString("transitionName");
            ViewCompat.setTransitionName(root, transitionName);
        }
        topAppBar = root.findViewById(R.id.topAppBar);
        topToolBar = root.findViewById(R.id.topToolBar);
        fab = root.findViewById(R.id.fab);
        topAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {
                //Initialize the size of the scroll
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                //Check if the view is collapsed
                if (scrollRange + verticalOffset == 0) {
                    fab.hide();
                    updateMenuItemIcons(R.color.color_on_surface);
                    topToolBar.getNavigationIcon().setTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.color_on_surface)));
                } else {
                    updateMenuItemIcons(R.color.color_on_image);
                    fab.show();
                    topToolBar.getNavigationIcon().setTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.color_on_image)));

                }
            }
        });
    }

    private void updateMenuItemIcons(int colorResId) {
        Menu menu = topToolBar.getMenu();

        for (int i = 0; i < topToolBar.getMenu().size(); i++) {
            MenuItem item = topToolBar.getMenu().getItem(i);
            item.setIconTintList(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), colorResId)));
        }
        topToolBar.invalidateMenu();
    }

}