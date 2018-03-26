package br.com.ladoleste.simpleredditclient.ui.contracts

import br.com.ladoleste.simpleredditclient.common.Category
import br.com.ladoleste.simpleredditclient.common.NewsItem

/**
 * Created by Anderson on 23/03/2018
 */
interface MainView {
    fun getCategory(): String
    fun getAfter(): String
    fun showError(it: Throwable)
    fun showList(it: List<NewsItem>?)
    var category: Category
    var loadingEnabled: Boolean
}