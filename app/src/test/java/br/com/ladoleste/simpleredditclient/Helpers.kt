package br.com.ladoleste.simpleredditclient

import java.io.*

/**
 *Created by Anderson on 15/02/2018.
 */
object Helpers {

    fun readFile(fileName: String = "response"): String {
        val datax = StringBuffer("")
        try {

            val classLoader = Helpers::class.java.classLoader
            val resource = classLoader.getResource("$fileName.json")
            val file = File(resource!!.path)

            val fIn = FileInputStream(file)
            val isr = InputStreamReader(fIn)
            val buffreader = BufferedReader(isr)

            var readString = buffreader.readLine()
            while (readString != null) {
                datax.append(readString)
                readString = buffreader.readLine()
            }

            isr.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

        return datax.toString()
    }
}
