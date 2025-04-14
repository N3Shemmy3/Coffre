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
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseFragment extends Fragment {
    protected BaseFragment() {
    }

    protected abstract int getLayoutResId();

    public void onCreateFragment(@NonNull View root, @Nullable Bundle savedInstanceState){}

    protected View root;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewCompat.setTransitionName(root, null);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutResId(), container, false);
        onCreateFragment(root, savedInstanceState);
        return root;
    }

    @NonNull
    public FragmentManager getSupportFragmentManager() {
        return requireActivity().getSupportFragmentManager();
    }
}
