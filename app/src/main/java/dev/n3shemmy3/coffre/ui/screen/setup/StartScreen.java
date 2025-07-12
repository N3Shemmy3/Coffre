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

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.button.MaterialButton;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class StartScreen extends BaseScreen {


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_start;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        InsetsUtils.applyContentInsets(root);
        ImageView headerIcon = root.findViewById(R.id.headerIcon);
        TickerView ticker = root.findViewById(R.id.ticker);
        TextView tagline = root.findViewById(R.id.tagline);
        MaterialButton actionStart = root.findViewById(R.id.actionStart);

        ticker.setCharacterLists(TickerUtils.provideAlphabeticalList());

        if (state == null) {
            ticker.setText("ABCDE");
            // Prepare the View for the animation
            headerIcon.setAlpha(0.0f);
            headerIcon.setTranslationY((144));

            ticker.setAlpha(0.0f);
            ticker.setTranslationY((60));

            tagline.setAlpha(0.0f);
            tagline.setTranslationY((30));

            actionStart.setAlpha(0.0f);
            actionStart.setTranslationY((30));

// Start the animation
            headerIcon.animate()
                    .translationY(0)
                    .alpha(1.0f)
                    .setDuration(850)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(@NonNull Animator animator) {
                            ticker.animate()
                                    .translationY(0)
                                    .alpha(1.0f)
                                    .setDuration(1000)
                                    .setListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(@NonNull Animator animator) {
                                            ticker.setText("Coffre");

                                        }

                                        @Override
                                        public void onAnimationEnd(@NonNull Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationCancel(@NonNull Animator animator) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(@NonNull Animator animator) {

                                        }
                                    });

                        }

                        @Override
                        public void onAnimationEnd(@NonNull Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(@NonNull Animator animator) {
                        }

                        @Override
                        public void onAnimationRepeat(@NonNull Animator animator) {

                        }
                    });
            ticker.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animator) {

                }

                @Override
                public void onAnimationEnd(@NonNull Animator animator) {
                    tagline.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setDuration(750)
                            .setListener(null);
                    actionStart.animate()
                            .translationY(0)
                            .alpha(1.0f)
                            .setDuration(750)
                            .setListener(null);
                }

                @Override
                public void onAnimationCancel(@NonNull Animator animator) {

                }

                @Override
                public void onAnimationRepeat(@NonNull Animator animator) {

                }
            });
        } else {
            ticker.setText("Coffre");
        }
        root.findViewById(R.id.actionStart).setOnClickListener(v -> {
            ViewCompat.performHapticFeedback(v, HapticFeedbackConstantsCompat.CONTEXT_CLICK);
            Navigator.push(getScreenManager(), new SetupScreen());
        });
    }
}
