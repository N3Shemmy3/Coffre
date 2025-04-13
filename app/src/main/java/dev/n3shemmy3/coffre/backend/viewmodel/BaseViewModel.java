package dev.n3shemmy3.coffre.backend.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.reactive.DataSubscription;

public abstract class BaseViewModel extends ViewModel {

    private final List<DataSubscription> mSubscriptions;

    @Override
    protected void onCleared() {
        super.onCleared();
        for (DataSubscription subscription : mSubscriptions) {
            if (!subscription.isCanceled()) {
                subscription.cancel();
            }
        }
    }

    protected final void addSubscription(@NonNull DataSubscription subscription) {
        mSubscriptions.add(subscription);
    }

    public BaseViewModel() {
        mSubscriptions = new ArrayList<>();
    }
}