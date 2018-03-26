package br.com.ladoleste.simpleredditclient.ui.contracts

/**
 * Created by Anderson on 23/03/2018
 */
interface CommentsPresenter : LifecyclePresenter {
    fun loadComments()
    fun setView(view: CommentsView)
}