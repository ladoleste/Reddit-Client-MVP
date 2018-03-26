package br.com.ladoleste.simpleredditclient.viewmodel

import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Anderson on 14/02/2018.
 */
open class BaseViewModel {

    val cDispose = CompositeDisposable()

    fun dispose() {
        cDispose.clear()
    }
}