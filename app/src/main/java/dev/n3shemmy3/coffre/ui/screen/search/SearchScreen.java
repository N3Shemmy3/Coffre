package dev.n3shemmy3.coffre.ui.screen.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.adapters.TransactionsAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.interfaces.ItemListener;
import dev.n3shemmy3.coffre.ui.interfaces.TextChangedListener;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;

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
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    protected void onScreenCreated(View root, Bundle state) {
        super.onScreenCreated(root, state);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    OnBackPressedCallback callback = new OnBackPressedCallback(false) {
        @Override
        public void handleOnBackPressed() {
            if (searchBar.getVisibility() == View.VISIBLE) {
                showSearchBar(false);
            } else {
                navigateUp();
            }
        }
    };

    private void setUpSearchBar() {
        searchItem = topToolBar.getMenu().findItem(R.id.action_search);
        Button clearButton = root.findViewById(R.id.clearButton);

        topToolBar.setNavigationOnClickListener(view -> {
            if (searchBar.getVisibility() == View.VISIBLE) showSearchBar(false);
            else navigateUp();

        });
        topToolBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_search) showSearchBar(true);
            return true;
        });
        clearButton.setOnClickListener(view -> {
            searchView.setText("");
            //AppUtils.showSoftInput(requireActivity(), searchView, true);
        });
        searchView.addTextChangedListener(new TextChangedListener<>(searchView) {
            @Override
            public void onTextChanged(EditText target, Editable s) {
                String query = target.getText().toString().trim();
                boolean startSearch = query.length() > 2;
                clearButton.setVisibility(startSearch ? View.VISIBLE : View.INVISIBLE);
                //performSearch(query);
            }
        });
        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                AppUtils.showSoftInput(requireActivity(), searchView, false);
                if (!TextUtils.isEmpty(v.getText().toString())) {
                    performSearch(String.valueOf(searchView.getText()));
                } else {
                    showSearchBar(false);
                }
            }
            return true;
        });
    }

    private void showSearchBar(boolean visible) {
        if (searchItem == null) return;
        callback.setEnabled(visible);
        if (visible) {
            searchBar.setVisibility(View.VISIBLE);
            topToolBar.getMenu().clear();
            AppUtils.showSoftInput(requireActivity(), searchView, true);
            searchView.requestFocus();
        } else {
            topToolBar.inflateMenu(R.menu.category_toolbar);
            AppUtils.showSoftInput(requireActivity(), searchView, false);
            searchBar.setVisibility(View.GONE);
        }
    }

    private void performSearch(String query) {
        viewModel.searchTransactions(query).observe(getViewLifecycleOwner(), items -> adapter.submitList(items));
    }

    private void setUpRecycler() {
        recycler.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new TransactionsAdapter();
        adapter.setItemListener(this);
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
