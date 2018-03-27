package br.com.ladoleste.simpleredditclient.features.newslist

import br.com.ladoleste.simpleredditclient.common.NewsItem
import br.com.ladoleste.simpleredditclient.features.comments.AdapterConstants.LOADING_ITEM
import br.com.ladoleste.simpleredditclient.features.common.BasePresenter
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Anderson on 23/03/2018
 */
class MainPresenterImpl(private val repository: RedditRepository) : MainPresenter, BasePresenter() {

    private lateinit var view: MainView
    private var currentItems = emptyList<NewsItem>()
    private var lastAfter = ""

    private val loadingItem = object : NewsItem {
        override val getType: Int
            get() = LOADING_ITEM
    }

    override fun resume() {
        loadNews(false)
    }

    override fun destroy() {
        dispose()
    }

    override fun setView(view: MainView) {
        this.view = view
    }

    override fun loadNews(refresh: Boolean) {
        if (refresh) {
            lastAfter = ""
        }

        val disposable = repository.getNews(view.category, lastAfter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    lastAfter = it.dataHolderList.after ?: ""
                    val newItems = it.dataHolderList.children.map { it.data.toNews() }

                    var items = currentItems.toMutableList()

                    if (refresh)
                        items = newItems.toMutableList()
                    else if (!items.isEmpty()) {
                        items.remove(items.last())
                        items.addAll(newItems)
                    } else {
                        items = newItems.toMutableList()
                    }

                    if (lastAfter.isNotEmpty())
                        items.add(loadingItem)

                    view.loadingEnabled = lastAfter.isNotEmpty()

                    currentItems = items
                    view.showList(currentItems)
                }, {
                    view.showError(it)
                })

        cDispose.add(disposable)
    }
}