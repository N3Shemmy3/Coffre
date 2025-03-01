package dev.n3shemmy3.coffre.ui.screen.record;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Objects;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Transaction;
import dev.n3shemmy3.coffre.backend.item.viewmodel.TransactionViewModel;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.category.CategoryScreen;
import dev.n3shemmy3.coffre.ui.utils.DateUtils;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class RecordScreen extends BaseScreen {

    private TransactionViewModel viewModel;

    private TextInputEditText inputTitle;
    private TextInputEditText inputAmount;
    private TabLayout tabLayout;
    private AutoCompleteTextView inputTime;
    private AutoCompleteTextView inputDate;
    private TextInputEditText inputNotes;
    private AutoCompleteTextView inputCategory;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_record;
    }

    @Override
    protected void onCreateScreen(View root, Bundle savedInstanceState) {
        super.onCreateScreen(root, savedInstanceState);
        inputTitle = root.findViewById(R.id.inputTitle);
        inputAmount = root.findViewById(R.id.inputAmount);
        tabLayout = root.findViewById(R.id.tabLayout);
        inputTime = root.findViewById(R.id.inputTime);
        inputDate = root.findViewById(R.id.inputDate);
        inputNotes = root.findViewById(R.id.inputNotes);
        inputCategory = root.findViewById(R.id.inputCategory);

        setUpTabs();
        setUpDateTimePickers();
        setUpCategory();
        InsetsUtils.applyImeInsets(requireActivity().getWindow(), root);
    }

    @Override
    protected void onScreenCreated(View root, Bundle state) {
        super.onScreenCreated(root, state);
        viewModel = new ViewModelProvider(requireActivity()).get(TransactionViewModel.class);
        viewModel.getSelectedItem().observe(this, item -> {
            if (item == null) return;
            inputTitle.setText(item.getTitle());
            inputAmount.setText(new DecimalFormat("#.00").format(item.getAmount()));

        });
    }

    private void setUpTabs() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {


            }

            @Override
            public void onTabUnselected(@NonNull TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(@NonNull TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //cleanup
        viewModel.selectItem(null);
    }

    private void setUpDateTimePickers() {
        boolean is24HourFormat = DateFormat.is24HourFormat(requireContext());
        Calendar now = DateUtils.getCurrentTime();

        // Set default values using system format
        inputTime.setText(DateUtils.formatTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), is24HourFormat));
        inputDate.setText(DateUtils.formatDate(now.getTimeInMillis(), requireContext()));

        // Fix double-click delay issues
        inputTime.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });
        inputDate.setOnFocusChangeListener((view, isFocused) -> {
            if (view.isInTouchMode() && isFocused) view.performClick();
        });

        inputDate.setOnClickListener(v -> {
            CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                    .setStart(0L) // Start from epoch time (optional, ensures full range)
                    .setEnd(now.getTimeInMillis()); // Restrict future dates

            MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(now.getTimeInMillis()) // Default to today
                    .setCalendarConstraints(constraintsBuilder.build()) // Apply constraints
                    .build();

            datePicker.addOnPositiveButtonClickListener(selection -> {
                if (selection > now.getTimeInMillis()) {
                    selection = now.getTimeInMillis(); // Prevent setting future date manually
                }

                inputDate.setText(DateUtils.formatDate(selection, requireContext()));

                // Reset time if today is selected
                if (DateUtils.isToday(selection)) {
                    inputTime.setText(DateUtils.formatTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), is24HourFormat));
                }
            });

            datePicker.show(getParentFragmentManager(), "DATE_PICKER_TAG");
        });


        inputTime.setOnClickListener(v -> {
            Calendar selectedDate = Calendar.getInstance();
            try {
                selectedDate.setTime(Objects.requireNonNull(DateFormat.getDateFormat(requireContext()).parse(inputDate.getText().toString())));
            } catch (ParseException e) {
                selectedDate.setTimeInMillis(now.getTimeInMillis());
            }

            boolean isToday = DateUtils.isToday(selectedDate.getTimeInMillis());

            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(is24HourFormat ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H)
                    .setHour(isToday ? now.get(Calendar.HOUR_OF_DAY) : 0)
                    .setMinute(isToday ? now.get(Calendar.MINUTE) : 0)
                    .setTitleText("Select time")
                    .build();

            timePicker.addOnPositiveButtonClickListener(picker -> {
                int pickedHour = timePicker.getHour();
                int pickedMinute = timePicker.getMinute();

                // Prevent selecting future times if today is chosen
                if (isToday && (pickedHour > now.get(Calendar.HOUR_OF_DAY) ||
                        (pickedHour == now.get(Calendar.HOUR_OF_DAY) && pickedMinute > now.get(Calendar.MINUTE)))) {
                    pickedHour = now.get(Calendar.HOUR_OF_DAY);
                    pickedMinute = now.get(Calendar.MINUTE);
                }

                inputTime.setText(DateUtils.formatTime(pickedHour, pickedMinute, is24HourFormat));
            });

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


}
