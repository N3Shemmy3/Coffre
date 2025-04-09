package dev.n3shemmy3.coffre.ui.screen.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.base.BaseFragment;

public class MainBalanceCard extends BaseFragment {
    private MainViewModel viewModel;

    private TickerView round;
    private TickerView decimal;
    private TickerView income, expenses;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main_balance_card;
    }

    @Override
    public void onCreateFragment(@NonNull View root, @Nullable Bundle savedInstanceState) {
        round = root.findViewById(R.id.round);
        decimal = root.findViewById(R.id.decimal);
        income = root.findViewById(R.id.income);
        expenses = root.findViewById(R.id.expenses);
        round.setCharacterLists(TickerUtils.provideNumberList());
        decimal.setCharacterLists(TickerUtils.provideNumberList());
        income.setCharacterLists(TickerUtils.provideNumberList());
        expenses.setCharacterLists(TickerUtils.provideNumberList());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel.getNetBalance().observe(getViewLifecycleOwner(), netBalance -> {
            BigDecimal formattedBalance = formatAmount(netBalance);
            BigDecimal intPart = new BigDecimal(formattedBalance.toBigInteger());
            round.setText(String.valueOf(intPart.intValue()));
            decimal.setText(String.valueOf(getAbsDecimalPart(formattedBalance)).replaceFirst("^0", ""));
        });
        viewModel.getTotalIncome().observe(getViewLifecycleOwner(), incomeBalance -> income.setText("$" + formatAmount(incomeBalance)));
        viewModel.getTotalExpenses().observe(getViewLifecycleOwner(), expenseBalance -> expenses.setText("$" + formatAmount(expenseBalance)));

    }

    public BigDecimal getAbsDecimalPart(BigDecimal bigDecimal) {
        BigDecimal absoluteBigDecimal = bigDecimal.abs();
        BigDecimal intPart = new BigDecimal(absoluteBigDecimal.toBigInteger());
        BigDecimal decimalPart = absoluteBigDecimal.subtract(intPart);
        return decimalPart.setScale(decimalPart.scale(), RoundingMode.DOWN);
    }

    private BigDecimal formatAmount(BigDecimal balance) {
        return String.valueOf(balance).equals("null") ? BigDecimal.valueOf(0.00) : balance.setScale(2, RoundingMode.DOWN);
    }
}
