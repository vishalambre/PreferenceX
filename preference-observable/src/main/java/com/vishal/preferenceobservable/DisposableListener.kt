package com.vishal.preferenceobservable

import androidx.annotation.CheckResult
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.atomic.AtomicBoolean

/** @author Vishal Ambre */

abstract class DisposableListener : Disposable {

    private val unsubscribed = AtomicBoolean()

    @CheckResult
    override fun isDisposed() = unsubscribed.get()

    override fun dispose() {
        if (unsubscribed.compareAndSet(false, true)) {
            onDispose()
        }
    }

    abstract fun onDispose()
}