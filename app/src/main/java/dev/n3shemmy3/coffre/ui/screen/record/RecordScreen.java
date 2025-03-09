package dev.n3shemmy3.coffre.ui.screen.record;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.interfaces.TextChangedListener;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.category.CategoryScreen;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class RecordScreen extends BaseScreen {

    private MainViewModel viewModel;
    private Transaction item = new Transaction();
    private Button action_save;
    private TextInputLayout textTitle;
    private TextInputEditText inputTitle;
    private TextInputLayout textAmount;
    private TextInputEditText inputAmount;
    private TabLayout tabLayout;
    private AutoCompleteTextView inputTime;
    private AutoCompleteTextView inputDate;
    private TextInputEditText inputNotes;
    private AutoCompleteTextView inputCategory;

    private MaterialDatePicker<Long> datePicker;
    private MaterialTimePicker timePicker;


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_record;
    }

    @Override
    protected void onCreateScreen(View root, Bundle savedInstanceState) {
        super.onCreateScreen(root, savedInstanceState);
        action_save = root.findViewById(R.id.action_save);
        inputTitle = root.findViewById(R.id.inputTitle);
        inputAmount = root.findViewById(R.id.inputAmount);
        tabLayout = root.findViewById(R.id.tabLayout);
        textTitle = root.findViewById(R.id.textTitle);
        inputTime = root.findViewById(R.id.inputTime);
        textAmount = root.findViewById(R.id.textAmount);
        inputDate = root.findViewById(R.id.inputDate);
        inputNotes = root.findViewById(R.id.inputNotes);
        inputCategory = root.findViewById(R.id.inputCategory);

        InsetsUtils.applyImeInsets(requireActivity().getWindow(), root);
    }

    @Override
    protected void onScreenCreated(View root, Bundle state) {
        super.onScreenCreated(root, state);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        Bundle args = getArguments();
        if (args != null) {
            Transaction receivedItem = args.getParcelable("item");
            if (receivedItem != null && receivedItem.getId() != 0) {
                item = receivedItem;
                populateFieldsWithItemData(item);
            }
        }
        setUpDateTimePickers();
        setUpCategory();
    }

    private void populateFieldsWithItemData(Transaction item) {
        inputTitle.setText(item.getTitle().trim());
        inputAmount.setText(new DecimalFormat("#.00").format(item.getAmount()));
        inputNotes.setText(item.getDescription().trim());
        tabLayout.selectTab(tabLayout.getTabAt(getSelectedTab(item.getTransactionType())));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(item.getTime());
        inputTime.setText(DateUtils.formatTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), DateUtils.is24HourFormat(requireContext())));
        inputDate.setText(DateUtils.formatDate(calendar.getTimeInMillis(), requireContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (areInputsEmpty()) return;
        //Create transaction item
        item.setId(item.getId() == 0 ? (int) System.currentTimeMillis() : item.getId());
        item.setTitle(String.valueOf(inputTitle.getText()).trim());
        item.setDescription(String.valueOf(inputNotes.getText()).trim());
        item.setAmount(new BigDecimal(String.valueOf(inputAmount.getText())));
        item.setAccountId(0);
        item.setTransactionType(Transaction.TransactionType.values()[tabLayout.getSelectedTabPosition()]);
        item.setTime(DateUtils.getTimeMillis(datePicker, timePicker));
        viewModel.addItem(item);

        //also save to bundle
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        setArguments(args);
    }

    private void setUpDateTimePickers() {
        boolean is24HourFormat = DateFormat.is24HourFormat(requireContext());
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(item.getTime() == 0 ? System.currentTimeMillis() : item.getTime());

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setStart(0L) // Start from epoch time (optional, ensures full range)
                .setEnd(calender.getTimeInMillis()); // Restrict future dates

        datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(calender.getTimeInMillis())
                .setCalendarConstraints(constraintsBuilder.build()) // Apply constraints
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            if (selection > calender.getTimeInMillis()) {
                selection = calender.getTimeInMillis(); // Prevent setting future date manually
            }

            inputDate.setText(DateUtils.formatDate(selection, requireContext()));
            // Reset time if today is selected
            if (DateUtils.isToday(selection)) {
                inputTime.setText(DateUtils.formatTime(calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), is24HourFormat));
            }
        });
        timePicker = new MaterialTimePicker.Builder()
                .setTitleText("Select time")
                .setTimeFormat(is24HourFormat ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H)
                .setHour(calender.get(Calendar.HOUR_OF_DAY))
                .setMinute(calender.get(Calendar.MINUTE))
                .build();

        timePicker.addOnPositiveButtonClickListener(picker -> {
            int pickedHour = timePicker.getHour();
            int pickedMinute = timePicker.getMinute();
            inputTime.setText(DateUtils.formatTime(pickedHour, pickedMinute, is24HourFormat));
            timePicker.setHour(pickedHour);
            timePicker.setMinute(pickedMinute);
        });

        // Set default values using system format
        inputTime.setText(DateUtils.formatTime(calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), is24HourFormat));
        inputDate.setText(DateUtils.formatDate(calender.getTimeInMillis(), requireContext()));

        // Fix double-click delay issues
        inputTime.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });
        inputDate.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });

        inputDate.setOnClickListener(v -> {
            datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG");
        });


        inputTime.setOnClickListener(v -> {
            timePicker.show(getParentFragmentManager(), "TIME_PICKER_TAG");
        });
    }

    private void setUpCategory() {
        // Fix double-click delay issues
        inputCategory.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });
        inputCategory.setOnClickListener(view -> Navigator.push(getNavigator(), new CategoryScreen()));
    }

    private boolean areInputsEmpty() {
        String texts = inputTitle.getText().toString() + inputAmount.getText().toString();
        return texts.isEmpty();
    }

    private int getSelectedTab(Transaction.TransactionType type) {
        int selection = 0;
        if (type == Transaction.TransactionType.EXPENSE) selection = 1;
        else if (type == Transaction.TransactionType.TRANSFER) selection = 2;
        return selection;
    }


}
