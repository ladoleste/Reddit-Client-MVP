package br.com.ladoleste.simpleredditclient

import android.content.Context
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader


/**
 *Created by Anderson on 19/02/2018.
 */
object Util {

    fun readFileFromAssets(cx: Context, fileName: String, indice: Int = 0): String {
        val builder = StringBuilder()
        try {
            val stream = cx.assets.open(fileName)
            val bReader = BufferedReader(InputStreamReader(stream, "UTF-8") as Reader?)
            var line: String?
            while (true) {
                line = bReader.readLine()
                if (line == null) {
                    break
                }
                builder.append(line)
            }
        } catch (e: IOException) {
            Timber.e(e)
        }

        return builder.toString().substring(indice)
    }
}
