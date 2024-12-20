package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.Hold;
import com.google.android.material.transition.MaterialElevationScale;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.activity.MainActivity;
import dev.n3shemmy3.coffre.ui.adapter.AccountsViewPagerAdapter;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;
import dev.n3shemmy3.coffre.ui.viewholder.AccountItemViewHolder;
import dev.n3shemmy3.coffre.ui.viewholder.BaseViewHolder;


public class MainFragment extends BaseFragment {


    private ViewPager2 accountsViewPager;
    private AccountsViewPagerAdapter accountsViewPagerAdapter;
    private FloatingActionButton floatingActionButton;
    private final Hold holdTransition = new Hold();


    @Override
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        accountsViewPager = root.findViewById(R.id.accountsViewPager);
        accountsViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        accountsViewPager.setOffscreenPageLimit(2);
        accountsViewPager.setPageTransformer((page, position) -> {
            float offset = 48 * position;
            page.setTranslationX(-offset);
        });
        accountsViewPager.setHorizontalFadingEdgeEnabled(true);
        accountsViewPager.setFadingEdgeLength(30);
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            items.add("Item " + i);
        }
        AccountsViewPagerAdapter adapter = new AccountsViewPagerAdapter(requireContext(), items);
        adapter.setItemListener(new ItemListener() {
            @Override
            public void onItemClicked(@NonNull View itemView, Object object, int position) {
                MaterialElevationScale exitTransition = new MaterialElevationScale(false);
                exitTransition.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));
                setExitTransition(exitTransition);
                holdTransition.addTarget(root);
                holdTransition.setDuration(exitTransition.getDuration());

                MaterialElevationScale reenterTransition = new MaterialElevationScale(true);
                reenterTransition.setDuration(getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1));
                setReenterTransition(reenterTransition);


                String transitionName = "shared_element_" + position;
                ViewCompat.setTransitionName(itemView, transitionName);
                Bundle bundle = new Bundle();
                bundle.putString("transitionName", transitionName);
                ((MainActivity) requireActivity()).replaceFragment(new AccountFragment(), itemView, bundle);
            }

            @Override
            public void onItemLongClicked(@NonNull View itemView, Object object, int position) {

            }
        });
        accountsViewPager.setAdapter(adapter);

        floatingActionButton = root.findViewById(R.id.fab);
        Bundle bundle = new Bundle();
        bundle.putString("transitionName", "fab");
        floatingActionButton.setTransitionName("fab");
        floatingActionButton.setOnClickListener(v -> ((MainActivity) requireActivity()).replaceFragment(new RecordFragment(), floatingActionButton, bundle));

        InsetUtils.applySystemBarsInsets(root.findViewById(R.id.topAppBar), false, true, false, false);
        InsetUtils.applySystemBarsMargin(floatingActionButton, false, false, false, true);
    }


}