package com.dimatest.movieapp.common;

import androidx.core.util.Preconditions;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class BaseUseCaseSingle<T, Params> extends BaseUseCase {

    BaseUseCaseSingle(Scheduler mainThread, Scheduler ioThread) {
        super(mainThread, ioThread);
    }

    abstract Single<T> buildUseCaseObservable(Params params);

    public void execute(DisposableSingleObserver<T> observer, Params params) {
        Preconditions.checkNotNull(observer);
        final Single<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(ioThread)
                .observeOn(mainThread)
                .doOnSubscribe(__ -> isLoading.postValue(true))
                .doOnTerminate(() -> isLoading.postValue(true))
                .doOnDispose(() -> isLoading.postValue(false));
        addDisposable(observable.subscribeWith(observer));
    }
}
