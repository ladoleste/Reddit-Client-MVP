package br.com.ladoleste.simpleredditclient

import br.com.ladoleste.simpleredditclient.dto.Thing
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import br.com.ladoleste.simpleredditclient.ui.MainPresenterImpl
import br.com.ladoleste.simpleredditclient.ui.contracts.MainPresenter
import br.com.ladoleste.simpleredditclient.ui.contracts.MainView
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`

/**
 * Created by Anderson on 25/03/2018
 */
class PresenterTest {

    @Mock
    lateinit var repo: RedditRepository
    @Mock
    lateinit var view: MainView
    @Mock
    lateinit var presenter: MainPresenter
    @Mock
    lateinit var news: Thing

    @Before
    fun setup() {
        `when`(repo.getNews(any())).thenReturn(Single.just(news))
        presenter = MainPresenterImpl(repo)
        presenter.setView(view)
    }

    @Test
    fun test_this() {
        presenter.loadNews(false)
    }
}