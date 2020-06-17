package com.example.android.androidarchkotlin.remote.util

import androidx.collection.ArrayMap
import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

class ApiResponse<T> private constructor() {
    companion object {
        private val LINK_PATTERN = Pattern
                .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private val NEXT_LINK = "next"
    }

    var code: Int = 0
        private set
    var body: T? = null
        private set
    var errorMessage: String? = null
        private set
    var links: MutableMap<String, String> = Collections.emptyMap()
        private set
    var error: Throwable? = null
        private set

    constructor(error: Throwable) : this() {
        code = 500
        errorMessage = error.message
        this.error = error
    }

    constructor(code: Int, body: T? = null, errorMessage: String? = null, error: Throwable? = null) : this(){
        this.code = code
        this.body = body
        this.errorMessage = errorMessage
        this.error = error
    }

    constructor(response: Response<T>): this() {
        code = response.code()

        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()?.string()
                } catch (ignored: IOException) {
                    Log.e("ApiResponse", "error while parsing response", ignored)
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
        }

        val linkHeader = response.headers().get("link")
        if (linkHeader != null) {
            links = ArrayMap()
            val matcher = LINK_PATTERN.matcher(linkHeader)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links.put(matcher.group(2), matcher.group(1))
                }
            }
        }
    }

    val isSuccessful : Boolean
        get() = code in 200..299

    fun getNextPage(): Int? {
        val next = links[NEXT_LINK] ?: return null
        val matcher = PAGE_PATTERN.matcher(next)
        if (!matcher.find() || matcher.groupCount() != 1) {
            return null
        }
        return try {
            Integer.parseInt(matcher.group(1))
        } catch (ex: NumberFormatException) {
            null
        }

    }
}