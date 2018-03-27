package br.com.ladoleste.simpleredditclient.common

import android.util.Log
import timber.log.Timber

/**
 *Created by Anderson on 08/12/2017.
 * This creates a link in logcat that takes to the class that generated the log
 */
class ReleaseLog : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR)
            Log.println(priority, "MyApp", message)
    }
}