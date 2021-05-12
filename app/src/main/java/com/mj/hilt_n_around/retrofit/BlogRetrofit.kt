package com.mj.hilt_n_around.retrofit

import retrofit2.http.GET

interface BlogRetrofit {
    @GET("blogs")
    suspend fun get():List<BlogNetworkEntity>
}