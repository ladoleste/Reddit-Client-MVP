package br.com.ladoleste.simpleredditclient

import br.com.ladoleste.simpleredditclient.dto.Thing
import br.com.ladoleste.simpleredditclient.features.newslist.MainPresenter
import br.com.ladoleste.simpleredditclient.features.newslist.MainPresenterImpl
import br.com.ladoleste.simpleredditclient.features.newslist.MainView
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Anderson on 25/03/2018
 */
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var repo: RedditRepository
    @Mock
    private lateinit var view: MainView

    private lateinit var presenter: MainPresenter
    private lateinit var news: Thing

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        news = Gson().fromJson(Helpers.readFile(), Thing::class.java)

        presenter = MainPresenterImpl(repo)
        presenter.setView(view)
    }

    @Test
    fun testInterationsWithMainView() {
        `when`(repo.getNews(view.category, "")).thenReturn(Single.just(news))

        presenter.loadNews(false)
        verify(view).showList(any())
        verify(view, never()).showError(Throwable())
        verify(view, never()).getAfter()
        verify(view, times(2)).category
    }

    @Test(expected = RuntimeException::class)
    fun testInterationsWithMainViewWithError() {
        `when`(repo.getNews(view.category, "")).thenThrow(RuntimeException("Mock"))

        presenter.loadNews(false)
        verify(view, never()).showList(any())
        verify(view).showError(Throwable())
        verify(view, never()).getAfter()
        verify(view, times(2)).category
    }
}