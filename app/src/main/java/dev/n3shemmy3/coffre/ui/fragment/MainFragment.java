package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;


public class MainFragment extends BaseFragment {


    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "MainFragment.onViewCreated", Toast.LENGTH_SHORT).show();
        InsetUtils.applySystemBarsInsets(view.findViewById(R.id.topAppBar), false, true, false, false);
        InsetUtils.applySystemBarsMargin(view.findViewById(R.id.fab), false, false, false, true);
    }
}