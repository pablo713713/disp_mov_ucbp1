package com.calyrsoft.ucbp1.features.github.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Representa el JSON que devuelve GitHub
data class GithubUserResponse(
    val login: String,
    val avatar_url: String
)

// Retrofit API
interface GithubApi {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<GithubUserResponse>
}
