package br.com.ladoleste.simpleredditclient.dagger

import br.com.ladoleste.simpleredditclient.features.comments.CommentsPresenter
import br.com.ladoleste.simpleredditclient.features.comments.CommentsPresenterImpl
import br.com.ladoleste.simpleredditclient.features.newslist.MainPresenter
import br.com.ladoleste.simpleredditclient.features.newslist.MainPresenterImpl
import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Anderson on 21/03/2018
 */
@Module
class AppModule {

    @Provides
    fun provideMainPresenter(repository: RedditRepository): MainPresenter = MainPresenterImpl(repository)

    @Provides
    fun provideCommentsPresenter(repository: RedditRepository): CommentsPresenter = CommentsPresenterImpl(repository)
}