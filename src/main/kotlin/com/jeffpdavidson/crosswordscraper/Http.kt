package com.jeffpdavidson.crosswordscraper

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.w3c.xhr.ARRAYBUFFER
import org.w3c.xhr.TEXT
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/** Utilities for fetching content over HTTP. */
object Http {

    /** Exception thrown when a fetch fails (e.g. due to a network error or non-200 HTTP response code). */
    class HttpException(message: String) : Exception(message)

    /** Make a GET request to the given URL and return the result as a String. */
    suspend fun fetchAsString(url: String): String {
        return fetch(url, XMLHttpRequestResponseType.TEXT) as String
    }

    /** Make a GET request to the given URL and return the result as a ByteArray. */
    suspend fun fetchAsBinary(url: String): ByteArray {
        return Int8Array(fetch(url, XMLHttpRequestResponseType.ARRAYBUFFER) as ArrayBuffer).asDynamic() as ByteArray
    }

    private suspend fun fetch(url: String, responseType: XMLHttpRequestResponseType): dynamic {
        val request: XMLHttpRequest = suspendCoroutine { cont ->
            val xhr = XMLHttpRequest()
            xhr.responseType = responseType
            xhr.open("GET", url)
            xhr.onload = { _ -> cont.resume(xhr) }
            xhr.onerror = { _ -> cont.resumeWithException(HttpException("HTTP GET error from URL: $url")) }
            xhr.send()
        }
        if (request.status != 200.toShort()) {
            throw HttpException("HTTP GET error code ${request.status} from URL: $url")
        }
        return request.response
    }
}