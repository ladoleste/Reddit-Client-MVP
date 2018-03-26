package br.com.ladoleste.simpleredditclient

import br.com.ladoleste.simpleredditclient.common.CustomDeserializer
import br.com.ladoleste.simpleredditclient.dto.Thing
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import br.com.ladoleste.simpleredditclient.ui.CommentsPresenterImpl
import br.com.ladoleste.simpleredditclient.ui.contracts.CommentsPresenter
import br.com.ladoleste.simpleredditclient.ui.contracts.CommentsView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
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
class CommentsPresenterTest {

    @Mock
    private lateinit var repo: RedditRepository
    @Mock
    private lateinit var view: CommentsView

    private lateinit var presenter: CommentsPresenter
    private lateinit var comments: List<Thing>

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        val type = object : TypeToken<List<Thing>>() {}.type
        val gson = GsonBuilder().registerTypeAdapter(Thing::class.java, CustomDeserializer()).create()
        comments = gson.fromJson(Helpers.readFile("comments"), type)

        `when`(repo.getComments(view.id)).thenReturn(Single.just(comments))

        presenter = CommentsPresenterImpl(repo)
        presenter.setView(view)
    }

    @Test
    fun test_that() {
        presenter.loadComments()
        verify(view, times(2)).id
        verify(view, never()).showError(Throwable())
        verify(view).showComments(anyList())
        verify(view).showNews(any())
        verify(view, never()).showError(Throwable())
    }
}