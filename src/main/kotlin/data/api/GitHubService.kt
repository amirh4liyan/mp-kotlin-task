package edu.sharif.kotlin

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{username}/repos")
    suspend fun getUserInfo(
        @Path("username") username: String
    ): List<GitHubUser>
}