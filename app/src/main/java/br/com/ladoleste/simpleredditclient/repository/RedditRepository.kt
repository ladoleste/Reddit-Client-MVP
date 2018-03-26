package br.com.ladoleste.simpleredditclient.repository

import br.com.ladoleste.simpleredditclient.dto.Thing
import io.reactivex.Single

/**
 * Created by Anderson on 23/03/2018
 */
interface RedditRepository {
    fun getNews(category: String, after: String = "", limit: Int = 20): Single<Thing>
    fun getComments(id: String = ""): Single<List<Thing>>
}