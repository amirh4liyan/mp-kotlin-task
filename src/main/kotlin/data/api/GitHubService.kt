package edu.sharif.kotlin.data.api

import edu.sharif.kotlin.data.model.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{username}/repos")
    suspend fun getUser(
        @Path("username") username: String
    ): GitHubUser
}