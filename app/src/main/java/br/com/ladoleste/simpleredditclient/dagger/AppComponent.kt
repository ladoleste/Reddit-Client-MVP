package br.com.ladoleste.simpleredditclient.dagger

import br.com.ladoleste.simpleredditclient.repository.NetworkRedditRepositoryImpl
import br.com.ladoleste.simpleredditclient.ui.CommentsActivity
import br.com.ladoleste.simpleredditclient.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Anderson on 21/03/2018
 */

@Singleton
@Component(modules = [(RepositoryModule::class), (AppModule::class)])
interface AppComponent {
    fun inject(target: NetworkRedditRepositoryImpl)
    fun inject(target: MainActivity)
    fun inject(target: CommentsActivity)
}