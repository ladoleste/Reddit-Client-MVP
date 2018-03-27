package br.com.ladoleste.simpleredditclient.features.comments

import br.com.ladoleste.simpleredditclient.R
import br.com.ladoleste.simpleredditclient.common.CustomApplication.Companion.appContext

/**
 *Created by Anderson on 16/02/2018.
 */
fun getCommentsText(numComments: Int): String =
        when (numComments) {
            0 -> appContext.getString(R.string.no_comments_yet)
            1 -> appContext.getString(R.string.one_comment)
            else -> "$numComments ${appContext.getString(R.string.comments)}"
        }

object AdapterConstants {
    const val LOADING_ITEM = 0
    const val NEWS_ITEM_SELF = 1
    const val NEWS_ITEM = 2
}