package dev.n3shemmy3.coffre.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.transition.MaterialContainerTransform;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;


public class RecordFragment extends BaseFragment {

    private MaterialToolbar topToolBar;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_record;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int colorBackground = MaterialColors.getColor(requireContext(), android.R.attr.colorBackground, Color.TRANSPARENT);

        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.setAllContainerColors(colorBackground);
        transform.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));

        setSharedElementEnterTransition(transform);
        setSharedElementReturnTransition(transform);

    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        String transitionName = getArguments().getString("transitionName");
        ViewCompat.setTransitionName(root, transitionName);

        topToolBar = root.findViewById(R.id.topToolBar);
        topToolBar.setNavigationOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        InsetUtils.applySystemBarsInsets(root.findViewById(R.id.topAppBar), false, true, false, false);
    }
}