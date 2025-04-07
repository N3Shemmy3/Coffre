package dev.n3shemmy3.coffre.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.transition.MaterialSharedAxis;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public abstract class BaseScreen extends BaseFragment {

    public AppBarLayout topAppBar;
    public CollapsingToolbarLayout collToolBar;
    public MaterialToolbar topToolBar;
    public View content;

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

        // collToolBar = root.findViewById(R.id.collToolBar);
        content = root.findViewById(R.id.content);
        //Toolbar
        if (topToolBar != null) {
            topToolBar.setNavigationOnClickListener(v -> navigateUp());
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
        //Appbar
        if (topAppBar != null)
            InsetsUtils.applyAppbarInsets(topAppBar, topToolBar, collToolBar);
        //content below Appbar
        if (content != null) {
            InsetsUtils.applyContentInsets(root.findViewById(R.id.content));
        }
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
