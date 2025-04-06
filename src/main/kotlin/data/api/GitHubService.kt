// GitHubService.kt
package edu.sharif.kotlin.data.api

import edu.sharif.kotlin.data.model.GitHubUser
import edu.sharif.kotlin.data.model.GitHubRepo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String
    ): GitHubUser

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path("username") username: String
    ): List<GitHubRepo>
}
