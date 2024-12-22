package dev.n3shemmy3.coffre.ui.fragment;

import android.os.Bundle;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ConcatAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.Hold;
import com.google.android.material.transition.MaterialElevationScale;

import java.util.ArrayList;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.activity.MainActivity;
import dev.n3shemmy3.coffre.ui.adapter.AccountsViewPagerAdapter;
import dev.n3shemmy3.coffre.ui.adapter.ItemAdapter;
import dev.n3shemmy3.coffre.ui.adapter.RecyclerAdapter;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.items.BaseItem;
import dev.n3shemmy3.coffre.ui.items.SectionItem;
import dev.n3shemmy3.coffre.ui.spacer.SpacesItemDecoration;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;
import dev.n3shemmy3.coffre.ui.viewholder.SectionViewHolder;


public class MainFragment extends BaseFragment {


    private RecyclerView recyclerView;

    private RecyclerView recyclerTransactionHistory;
    private FloatingActionButton floatingActionButton;
    private final Hold holdTransition = new Hold();


    @Override
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(requireContext(),R.dimen.activity_padding_horizontal));
        ArrayList<SectionItem> sectionItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<BaseItem> items = new ArrayList<>();
            for (int a = 0; a < 6; a++) {
                items.add(new BaseItem());
            }
            sectionItems.add(new SectionItem("Section " + i, getResources().getString(R.string.see_all), items));
        }
        recyclerAdapter.submitList(sectionItems);

        /*
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
*/
        floatingActionButton = root.findViewById(R.id.fab);
        Bundle bundle = new Bundle();
        bundle.putString("transitionName", "fab");
        floatingActionButton.setTransitionName("fab");
        floatingActionButton.setOnClickListener(v -> ((MainActivity) requireActivity()).replaceFragment(new RecordFragment(), floatingActionButton, bundle));


        InsetUtils.applySystemBarsInsets(root.findViewById(R.id.topAppBar), false, true, false, false);
        InsetUtils.applySystemBarsMargin(floatingActionButton, false, false, false, true);
    }


}