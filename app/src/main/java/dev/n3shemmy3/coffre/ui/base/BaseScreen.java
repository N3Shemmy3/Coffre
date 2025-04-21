package dev.n3shemmy3.coffre.ui.base;
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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.MaterialSharedAxis;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public abstract class BaseScreen extends BaseFragment {

    public AppBarLayout topAppBar;
    public CollapsingToolbarLayout topToolBarLayout;
    public MaterialToolbar topToolBar;
    public View content;
    public FloatingActionButton fab;

    protected void onCreateScreen(View root, Bundle state) {
        applyInsets(root);
    }

    protected void onScreenCreated(View root, Bundle state) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable material transitions.
        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true));
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false));
        setExitTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true));
        setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutResId(), container, false);
        topAppBar = root.findViewById(R.id.topAppBar);
        topToolBar = root.findViewById(R.id.topToolBar);
        topToolBarLayout = root.findViewById(R.id.topToolBarLayout);
        fab = root.findViewById(R.id.fab);
        content = root.findViewById(R.id.content);
        //Toolbar
        if (topToolBar != null) {
            topToolBar.setNavigationOnClickListener(v -> {
                ViewCompat.performHapticFeedback(v, HapticFeedbackConstantsCompat.CONTEXT_CLICK);
                navigateUp();

            });
        }
        onCreateScreen(root, savedInstanceState);

        return root;
    }

    // https://github.com/material-components/material-components-android/issues/1984#issuecomment-1089710991
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Prevent previous content flicker when navigating.
        view.setBackgroundColor(MaterialColors.getColor(view, android.R.attr.colorBackground));
        onScreenCreated(view, savedInstanceState);
    }

    public void applyInsets(@NonNull View root) {
        if (topAppBar == null) return;
        InsetsUtils.applyInsets(topAppBar, topToolBar, topToolBarLayout, fab, content);
    }

    /*
        Why?
        Because its cool
     */
    @NonNull
    public FragmentManager getScreenManager() {
        return getSupportFragmentManager();
    }

    @NonNull
    public FragmentManager getChildScreenManager() {
        return getChildFragmentManager();
    }

    public void navigateUp() {
        topToolBar.performHapticFeedback(HapticFeedbackConstantsCompat.CONTEXT_CLICK);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

}
