package com.cognizant.dor.Common.Util

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by 653835 on 09/11/2017.
 */


object Rest {

    val JSON = MediaType.parse("application/json; charset=utf-8")
    var client = OkHttpClient()

    inline fun <reified T>convertResponse(json: String?): T? {
        try {
            return Gson().fromJson(json, T::class.java)
        } catch (e: Exception) {
            throw e
        }
    }

    //-------------------------------------------------------------------------------------------//
    /**
     * GETS
     */
    @JvmName("doGetWithJson")
    fun doGet(json: String, url: String): String? {
        logRequest()
        val request = Request.Builder()
                .url(url + json)
                .get()
                .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    @JvmName("doGet")
    fun doGet(url: String): String? {
        logRequest()
        val request = Request.Builder()
                .url(url)
                .get()
                .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    private fun logRequest() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @JvmName("doGetWithHeader")
    fun doGet(url: String, accessToken: String = "", carteirinha: String = ""): String? {
        logRequest()
        val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("Exemplo","bearer $accessToken")
                .addHeader("Exemplo", carteirinha)
                .build()
        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    //-----------------------------------------------------------------------------------------//

    /**
     * POST
     */
    @JvmName("doPostWithHeader")
    fun doPost(json: String, url: String, accessToken: String, username: String): String? {
        logRequest()
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder()
                .url(url)
                .addHeader("Exemplo","bearer $accessToken")
                .addHeader("Exemplo", username)
                .post(body)
                .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    @JvmName("doPostWithJson")
    fun doPost(json: String, url: String): String? {
        logRequest()
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    //-------------------------------------------------------------------------------------//
    @JvmName("doPutWithHeader")
    fun doPut(json: String, url: String, accessToken: String, username: String): String? {
        logRequest()
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder()
                .url(url)
                .addHeader("Exemplo","bearer $accessToken")
                .addHeader("Exemplo", username)
                .put(body)
                .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

}