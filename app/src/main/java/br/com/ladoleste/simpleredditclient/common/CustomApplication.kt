package br.com.ladoleste.simpleredditclient.common

import android.app.Application
import android.graphics.drawable.Drawable
import br.com.ladoleste.simpleredditclient.BuildConfig
import br.com.ladoleste.simpleredditclient.dagger.AppComponent
import br.com.ladoleste.simpleredditclient.dagger.AppModule
import br.com.ladoleste.simpleredditclient.dagger.DaggerAppComponent
import br.com.ladoleste.simpleredditclient.dagger.RepositoryModule
import com.facebook.stetho.Stetho
import timber.log.Timber

class CustomApplication : Application() {

    companion object {

        lateinit var component: AppComponent
            private set

        //used for the transitions activity animation
        var drawableHolder: Drawable? = null

        var apiUrl: String = BuildConfig.API_URL
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        Timber.plant(SuperLog())

        component = DaggerAppComponent.builder()
                .repositoryModule(RepositoryModule())
                .appModule(AppModule())
                .build()
    }
}