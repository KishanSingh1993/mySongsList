package com.example.mysonglist.retrofit

import com.example.mysonglist.model.Feed
//import retrofit2.Call
//import retrofit2.http.GET

/*interface ApiService {
    @GET("topsongs/limit=25/xml")
    suspend fun getTopSongs(): Call<Feed>
}*/

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("topsongs/limit=25/xml")
    fun getFeed(): Call<String>

    @GET("topsongs/limit=25/xml")
    suspend fun getTopSongs(): Feed
}