package br.com.ladoleste.simpleredditclient.features.comments

import br.com.ladoleste.simpleredditclient.features.common.LifecyclePresenter

/**
 * Created by Anderson on 23/03/2018
 */
interface CommentsPresenter : LifecyclePresenter {
    fun loadComments()
    fun setView(view: CommentsView)
}