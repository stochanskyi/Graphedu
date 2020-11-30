package com.nulp.graphedu.data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

typealias SingleList<T> = Single<List<T>>

fun <T> Flowable<T>.onComputationThread(): Flowable<T> = this
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.computation())

fun <T> Observable<T>.onComputationThread() = this
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.computation())

fun <T> Single<T>.onComputationThread() = this
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.computation())

fun <T> Maybe<T>.onComputationThread() = this
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.computation())

fun Completable.onComputationThread() = this
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.computation())

fun <T> Flowable<T>.onApiThread(): Flowable<T> = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Observable<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Single<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Maybe<T>.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun Completable.onApiThread() = this
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.io())

fun <T> Flowable<T>.onUiThread(): Flowable<T> = this
    .subscribeOn(AndroidSchedulers.mainThread())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.onUiThread() = this
    .subscribeOn(AndroidSchedulers.mainThread())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.onUiThread() = this
    .subscribeOn(AndroidSchedulers.mainThread())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.onUiThread() = this
    .subscribeOn(AndroidSchedulers.mainThread())
    .observeOn(AndroidSchedulers.mainThread())

fun Completable.onUiThread() = this
    .subscribeOn(AndroidSchedulers.mainThread())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun Completable.observeOnUI() = this
    .observeOn(AndroidSchedulers.mainThread())

fun Completable.andThenDeferred(source: () -> Completable) = andThen(
    Completable.defer { source() }
)

fun <T> Completable.andThenDeferredSingle(source: () -> Single<T>) = andThen(
    Single.defer { source() }
)

fun <T> Completable.andThenDeferredObservable(source: () -> Observable<T>) = andThen(
    Observable.defer { source() }
)

fun <T> Completable.andThenDeferredFlowable(source: () -> Flowable<T>) = andThen(
    Flowable.defer { source() }
)

fun Disposable.ignore() {}

val Disposable?.isRunning
    get() = this != null && !isDisposed