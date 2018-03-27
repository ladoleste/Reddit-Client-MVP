package br.com.ladoleste.simpleredditclient.features.common

import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Anderson on 14/02/2018.
 */
open class BasePresenter {

    val cDispose = CompositeDisposable()

    fun dispose() {
        cDispose.clear()
    }
}