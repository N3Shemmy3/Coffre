package dev.n3shemmy3.coffre.ui.screen.search;
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
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Transaction;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.adapter.TransactionsAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.interfaces.TextChangedListener;
import dev.n3shemmy3.coffre.ui.item.decorator.VerticalSpaceItemDecoration;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class SearchScreen extends BaseScreen implements ItemListener<Transaction> {
    private MenuItem searchItem;
    private LinearLayout searchBar;
    private EditText searchView;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private MainViewModel viewModel;
    private TransactionsAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_search;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        searchBar = root.findViewById(R.id.searchBar);
        searchView = root.findViewById(R.id.searchView);
        recycler = root.findViewById(R.id.recycler);
        setUpSearchBar();
        setUpRecycler();
        //requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        InsetsUtils.applyRecyclerInsets(recycler);
    }

    @Override
    protected void onScreenCreated(View root, Bundle state) {
        super.onScreenCreated(root, state);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        performSearch("");
    }

    @Override
    public void onPause() {
        super.onPause();
        AppUtils.showSoftInput(requireActivity(), searchView, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppUtils.showSoftInput(requireActivity(), searchView, true);
    }

    private void setUpSearchBar() {
        Button clearButton = root.findViewById(R.id.clearButton);

        clearButton.setOnClickListener(view -> {
            searchView.setText("");
            AppUtils.showSoftInput(requireActivity(), searchView, true);
        });
        searchView.addTextChangedListener(new TextChangedListener<>(searchView) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                String query = target.getText().toString().trim();
                clearButton.setVisibility(!query.isEmpty() ? View.VISIBLE : View.INVISIBLE);
                performSearch(query);
            }
        });
        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                AppUtils.showSoftInput(requireActivity(), searchView, false);
                if (!TextUtils.isEmpty(v.getText().toString())) {
                    performSearch(String.valueOf(searchView.getText()));
                }
            }
            return true;
        });
    }

    private void performSearch(String query) {
        viewModel.searchTransactions(query).observe(getViewLifecycleOwner(), items -> {
            items.sort(Comparator.comparing(Transaction::getTime).reversed());
            adapter.submitList(items);
        });
    }

    private void setUpRecycler() {
        recycler.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new TransactionsAdapter(true);
        adapter.setItemListener(this);
        recycler.addItemDecoration(new VerticalSpaceItemDecoration(4));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(@NonNull View itemView, Transaction item, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", item);
        Navigator.push(getSupportFragmentManager(), new RecordScreen(), bundle);
    }

    @Override
    public void onItemLongClicked(@NonNull View itemView, Transaction item, int position) {

    }
}
