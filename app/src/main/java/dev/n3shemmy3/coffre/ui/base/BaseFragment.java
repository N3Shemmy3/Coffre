package dev.n3shemmy3.coffre.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.transition.MaterialSharedAxis;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public abstract class BaseFragment extends Fragment {
    protected BaseFragment() {
    }

    protected abstract int getLayoutResId();

    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        applyInsets(root);
    }

    private View root;
    public AppBarLayout topAppBar;
    public CollapsingToolbarLayout collToolBar;
    public MaterialToolbar topToolBar;
    public View content;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewCompat.setTransitionName(root, null);
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
            topToolBar.setNavigationOnClickListener(v -> {
                topToolBar.performHapticFeedback(HapticFeedbackConstantsCompat.CONTEXT_CLICK);
                requireActivity().getSupportFragmentManager().popBackStack();
            });
        }
        onFragmentCreated(root, savedInstanceState);

        return root;
    }
    public void applyInsets(@NonNull View root) {
        //Appbar
        if (topAppBar != null)
            InsetsUtils.applyAppbarInsets(topAppBar, topToolBar, collToolBar);
        //content below Appbar
        if (content != null) {
            //InsetsUtils.applyContentInsets(root.findViewById(R.id.content));
        }
    }
    @NonNull
    public FragmentManager getSupportFragmentManager() {
        return requireActivity().getSupportFragmentManager();
    }
}
