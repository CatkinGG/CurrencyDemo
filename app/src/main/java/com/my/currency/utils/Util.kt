package com.my.currency.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object Util{
    fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String = try {
            val input: InputStream = context.getAssets().open(fileName)
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}