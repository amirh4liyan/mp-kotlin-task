package edu.sharif.kotlin

import edu.sharif.kotlin.data.api.GitHubService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton Design Pattern
object RetrofitHelper {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val gson: Gson by lazy {
        GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

    val githubService: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}