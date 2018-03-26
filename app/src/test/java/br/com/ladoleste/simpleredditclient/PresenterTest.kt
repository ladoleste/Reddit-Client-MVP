package br.com.ladoleste.simpleredditclient

import br.com.ladoleste.simpleredditclient.common.Category
import br.com.ladoleste.simpleredditclient.dto.Thing
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import br.com.ladoleste.simpleredditclient.ui.MainPresenterImpl
import br.com.ladoleste.simpleredditclient.ui.contracts.MainPresenter
import br.com.ladoleste.simpleredditclient.ui.contracts.MainView
import com.google.gson.Gson
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by Anderson on 25/03/2018
 */
class PresenterTest {

    lateinit var repo: RedditRepository
    lateinit var view: MainView
    lateinit var presenter: MainPresenter
    lateinit var news: Thing

    @Before
    fun setup() {
        repo = mock(RedditRepository::class.java)
        view = mock(MainView::class.java)
        news = Gson().fromJson(Helpers.readFile(), Thing::class.java)
        `when`(repo.getNews(Category.HOT.name)).thenReturn(Single.just(news))
        `when`(view.category).thenReturn(Category.HOT)
        presenter = MainPresenterImpl(repo)
        presenter.setView(view)
    }

    @Test
    fun test_this() {
        presenter.loadNews(false)
        assertEquals(view.getAfter(), "t3_7xjjyb")
    }
}