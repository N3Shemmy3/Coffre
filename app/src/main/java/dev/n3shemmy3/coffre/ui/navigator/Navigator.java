package dev.n3shemmy3.coffre.ui.navigator;
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
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import dev.n3shemmy3.coffre.R;

public class Navigator {

    public static void add(@IdRes int layoutId, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .addToBackStack(String.valueOf(System.currentTimeMillis()))
                .replace(layoutId, fragment)
                .commit();
    }

    public static void push(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        push(fragmentManager, fragment, null, null);
    }

    public static void push(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle args) {
        push(fragmentManager, fragment, null, args);
    }

    public static void push(@IdRes int layoutId, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        push(layoutId, fragmentManager, fragment, null, null);
    }

    public static void push(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable View sharedElement, @Nullable Bundle args) {
        push(R.id.fragmentContainer, fragmentManager, fragment, sharedElement, args);
    }

    public static void push(@IdRes int layoutId, @NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable View sharedElement, @Nullable Bundle args) {
        fragment.setArguments(args);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        if (sharedElement != null)
            transaction.addSharedElement(sharedElement, args.getString("transitionName"));
        transaction
                .addToBackStack(String.valueOf(System.currentTimeMillis()))
                .replace(layoutId, fragment)
                .commit();
    }
}
