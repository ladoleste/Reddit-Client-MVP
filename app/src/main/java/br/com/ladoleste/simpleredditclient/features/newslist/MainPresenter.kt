package br.com.ladoleste.simpleredditclient.features.newslist

import br.com.ladoleste.simpleredditclient.features.common.LifecyclePresenter

/**
 * Created by Anderson on 23/03/2018
 */
interface MainPresenter : LifecyclePresenter {
    fun loadNews(refresh: Boolean)
    fun setView(view: MainView)
}