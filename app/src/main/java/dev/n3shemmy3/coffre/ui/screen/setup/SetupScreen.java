package dev.n3shemmy3.coffre.ui.screen.setup;
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

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Profile;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.currency.CurrencyScreen;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;
import dev.n3shemmy3.coffre.ui.utils.FileUtils;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class SetupScreen extends BaseScreen {

    private CoordinatorLayout coordinator;
    private CollapsingToolbarLayout toolbarLayout;
    private ShapeableImageView avatar;
    private TextInputLayout textName;
    private TextInputEditText inputName;
    private Button actionPick;
    private FloatingActionButton actionNext;
    private Profile profile;


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_setup;
    }


    @Override
    protected void onCreateScreen(View root, Bundle state) {
        coordinator = root.findViewById(R.id.coordinator);
        actionNext = root.findViewById(R.id.actionNext);
        toolbarLayout = root.findViewById(R.id.toolbarLayout);
        avatar = root.findViewById(R.id.avatar);
        textName = root.findViewById(R.id.textName);
        inputName = root.findViewById(R.id.inputName);
        actionPick = root.findViewById(R.id.actionPick);
        actionPick.setOnClickListener(v -> {
            ViewCompat.performHapticFeedback(v, HapticFeedbackConstantsCompat.CONTEXT_CLICK);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);
        });
        actionNext.setOnClickListener(v -> {
            AppUtils.showSoftInput(requireActivity(), inputName, false);
            if (inputName.getText() == null || inputName.getText().toString().isEmpty()) {
                textName.setError("Please set a name");
            } else if (profile == null) {
                Snackbar.make(coordinator, "Please pick an image", Snackbar.LENGTH_SHORT).show();
            } else {
                PrefUtil.save(Profile.key, new Gson().toJson(profile));
                ViewCompat.performHapticFeedback(v, HapticFeedbackConstantsCompat.CONTEXT_CLICK);
                Navigator.push(getScreenManager(), new CurrencyScreen());

            }
        });
        //inputName.setOnFocusChangeListener((v, hasFocus) -> textName.setError(null));
        inputName.setOnClickListener(v -> textName.setError(null));
        applyInsets();
        profile = new Gson().fromJson(PrefUtil.getString(Profile.key), Profile.class);
        if (profile == null) return;
        Bitmap retrievedBitmap = FileUtils.retrieveImageFromPrivateStorage(requireContext(), profile.getAvatar());
        if (retrievedBitmap != null) {
            setAvatar(retrievedBitmap);
        }
        inputName.setText(profile.getName());
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            if (result.getResultCode() == Activity.RESULT_OK && data != null) {
                Uri uri = data.getData();
                try {
                    String filename = "avatar"; // Choose a filename (with extension)
                    Uri savedImageUri = FileUtils.saveImageToPrivateStorage(requireActivity(), uri, filename);
                    if (savedImageUri == null) return;
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), savedImageUri);
                    setAvatar(bitmap);
                    if (profile == null) profile = new Profile();
                    profile.setAvatar(filename);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private void applyInsets() {
        InsetsUtils.onInsetsListener(topAppBar, (windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            //Toolbar
            int dp8 = (int) (Resources.getSystem().getDisplayMetrics().density * 8);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) topToolBar.getLayoutParams();
            topToolBar.setPadding(hInsets + dp8, topToolBar.getPaddingTop(), hInsets + dp8, topToolBar.getPaddingBottom());
            toolbarLayout.setExpandedTitleMarginStart(hInsets + (dp8 * 3));
            toolbarLayout.setExpandedTitleMarginEnd(hInsets + (dp8 * 3));
            mlp.topMargin = systemBarInsets.top;
            topToolBar.setLayoutParams(mlp);

            //Fab
            mlp = (ViewGroup.MarginLayoutParams) actionNext.getLayoutParams();
            int dp16 = dp8 * 2;
            mlp.leftMargin = hInsets + dp16;
            mlp.rightMargin = hInsets + dp16;
            mlp.bottomMargin = dp16 + systemBarInsets.bottom + imeInsets.bottom;
            actionNext.setLayoutParams(mlp);
        });
        InsetsUtils.applyContentInsets(content);
    }

    private void setAvatar(Bitmap bitmap) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(requireActivity());
        circularProgressDrawable.setColorSchemeColors(MaterialColors.getColor(requireView(),com.google.android.material.R.attr.colorPrimary));
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(requireContext()).load(bitmap).placeholder(circularProgressDrawable).into(avatar);
    }
}
