package br.com.ladoleste.simpleredditclient.features.comments

import br.com.ladoleste.simpleredditclient.features.common.BasePresenter
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 *Created by Anderson on 14/02/2018.
 */
class CommentsPresenterImpl(private val repository: RedditRepository) : CommentsPresenter, BasePresenter() {

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