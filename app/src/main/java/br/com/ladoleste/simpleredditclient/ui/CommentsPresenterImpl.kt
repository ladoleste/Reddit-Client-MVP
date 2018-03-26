package br.com.ladoleste.simpleredditclient.ui

import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import br.com.ladoleste.simpleredditclient.ui.contracts.CommentsPresenter
import br.com.ladoleste.simpleredditclient.ui.contracts.CommentsView
import br.com.ladoleste.simpleredditclient.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 *Created by Anderson on 14/02/2018.
 */
class CommentsPresenterImpl(private val repository: RedditRepository) : CommentsPresenter, BaseViewModel() {

    private lateinit var view: CommentsView

    override fun resume() {
        loadComments()
    }

    override fun destroy() {
        dispose()
    }

    override fun loadComments() {
        cDispose.add(repository.getComments(view.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showNews(it.first().dataHolderList.children.map { it.data.toNews() }.single())
                    view.showComments(it.last().dataHolderList.children.filter { it.kind == "t1" }.map { it.data.toComments() })
                }, {
                    view.showError(it)
                }))
    }

    override fun setView(view: CommentsView) {
        this.view = view
    }
}