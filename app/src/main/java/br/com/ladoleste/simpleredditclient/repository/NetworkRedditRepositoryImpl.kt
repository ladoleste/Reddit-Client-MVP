package br.com.ladoleste.simpleredditclient.repository

import br.com.ladoleste.simpleredditclient.common.Category
import br.com.ladoleste.simpleredditclient.common.CustomApplication
import br.com.ladoleste.simpleredditclient.model.Api
import javax.inject.Inject

/**
 * Created by Anderson on 23/03/2018
 */
class NetworkRedditRepositoryImpl : RedditRepository {

    @Inject
    lateinit var api: Api

    init {
        CustomApplication.component.inject(this)
    }

    override
    fun getNews(category: Category, after: String, limit: Int) = api.getNews(category.name.toLowerCase(), after, limit)

    override
    fun getComments(id: String) = api.getComments(id)
}