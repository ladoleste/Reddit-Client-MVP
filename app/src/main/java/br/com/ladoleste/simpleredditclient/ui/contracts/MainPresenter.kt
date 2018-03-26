package br.com.ladoleste.simpleredditclient.ui.contracts

/**
 * Created by Anderson on 23/03/2018
 */
interface MainPresenter : LifecyclePresenter {
    fun loadNews(refresh: Boolean)
    fun setView(view: MainView)
}