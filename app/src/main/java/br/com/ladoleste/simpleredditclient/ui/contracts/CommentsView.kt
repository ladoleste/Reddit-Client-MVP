package br.com.ladoleste.simpleredditclient.ui.contracts

import br.com.ladoleste.simpleredditclient.dto.Comments
import br.com.ladoleste.simpleredditclient.dto.News

/**
 * Created by Anderson on 23/03/2018
 */
interface CommentsView {
    fun showError(it: Throwable)
    fun showNews(it: News?)
    fun showComments(it: List<Comments>?)
    var id: String
}