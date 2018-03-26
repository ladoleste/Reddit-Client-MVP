package br.com.ladoleste.simpleredditclient.dagger

import br.com.ladoleste.simpleredditclient.repository.RedditRepository
import br.com.ladoleste.simpleredditclient.ui.CommentsPresenterImpl
import br.com.ladoleste.simpleredditclient.ui.MainPresenterImpl
import br.com.ladoleste.simpleredditclient.ui.contracts.CommentsPresenter
import br.com.ladoleste.simpleredditclient.ui.contracts.MainPresenter
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