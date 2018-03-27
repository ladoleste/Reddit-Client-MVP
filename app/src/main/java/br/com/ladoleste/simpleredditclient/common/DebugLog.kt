package br.com.ladoleste.simpleredditclient.common

import android.util.Log
import timber.log.Timber

/**
 *Created by Anderson on 08/12/2017.
 * This creates a link in logcat that takes to the class that generated the log
 */
class DebugLog : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return super.createStackElementTag(element) + "|" + element.lineNumber
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        //allows navigation from logcat
        tag?.let {
            if (it.contains('|')) {
                Log.println(priority, "MyApp", String.format(".(%s.kt:%s) - %s", tag.split("|")[0].toRegex(), tag.split("|")[1].toRegex(), message))
            } else {
                Log.println(priority, tag, message)
            }
        } ?: Log.println(priority, "MyApp", message)

    }
}