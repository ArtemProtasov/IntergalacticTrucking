package com.example.intergalactictrucking.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public abstract class BaseViewModel extends ViewModel {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    MutableLiveData<Boolean> progressLoading = new MutableLiveData<Boolean>();
    MutableLiveData<Throwable> errorLoading = new MutableLiveData<Throwable>();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    protected Consumer<Deprecated> progressLoadingCunsumer() {
        return deprecated -> progressLoading.postValue(true);
    }

    protected Consumer<Throwable> errorLoadingConsumer() {
        return throwable -> {
            errorLoading.postValue(throwable);
            progressLoading.postValue(false);
        };
    }

    protected Action terminateProgress() {
        return () -> progressLoading.postValue(false);
    }
}
