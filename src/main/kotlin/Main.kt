// Main.kt
package edu.sharif.kotlin

import edu.sharif.kotlin.data.model.APIError
import edu.sharif.kotlin.data.model.GitHubRepo
import edu.sharif.kotlin.data.model.GitHubUser
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

// [DONE]
fun printRepoDetails(user: GitHubUser, repo: GitHubRepo) {
    println("""
            ┌──────────────────────────────────────────────────────────
            │ User: ${user.username}
            │ Repo: ${repo.name}
            │ URL: ${repo.url}
            └──────────────────────────────────────────────────────────
        """.trimIndent())
}

// [DONE]
fun printUserDetails(user: GitHubUser) {
    // AI Generated Pattern:
    println("""
            ┌───────────────────────────────────────────
            │ Username: ${user.username}
            │ Joined: ${user.joinedDate}
            │ Followers: ${user.followersCount}
            │ Following: ${user.followingCount}
            │ Public Repos: ${user.publicReposCount}
            └───────────────────────────────────────────
        """.trimIndent())
}

suspend fun getUser(username: String): GitHubUser? {
    try {
        return RetrofitHelper.githubService.getUserInfo(username)
    } catch (e: HttpException) {
        val error = RetrofitHelper.gson.fromJson(
            e.response()?.errorBody()?.string(),
            APIError::class.java
        )
        println(error)
    } catch (e: Exception) {
        println(e)
    }
    return null
}

suspend fun getRepos(username: String): List<GitHubRepo>? {
    try {
        return RetrofitHelper.githubService.getUserRepositories(username)
    } catch (e: HttpException) {
        val error = RetrofitHelper.gson.fromJson(
            e.response()?.errorBody()?.string(),
            APIError::class.java
        )
        println(error)
    } catch (e: Exception) {
        println(e)
    }
    return null
}

fun main(): Unit = runBlocking{
    val users: MutableMap<GitHubUser, List<GitHubRepo>> = mutableMapOf()
    var INPUT: String
    var CODE: Int
    while (true) {
        println("Select Option from Menu: ")
        println("  1) Get Data From Server Based on Username")
        println("  2) Show Available Users")
        println("  3) Search Based on Username")
        println("  4) Search Based on Repository Name")
        println("  5) Exit App")
        print("[In-Int] Enter Item Code (1 -> 5): ")
        INPUT = readln()
        try {
            CODE = INPUT.toInt()

            when (CODE) {
                1 -> {
                    print("[In-String] Enter Username: ")
                    val USERNAME = readln()
                    val user = getUser(USERNAME)
                    val repo = getRepos(USERNAME)
                    if (repo != null && user != null) {
                        users.put(user, repo)
                    }
                }
                2 -> {
                    users.forEach {
                        printUserDetails(it.key)
                    }
                }
                3 -> {
                    print("[In-String] Enter username to search: ")
                    val query = readln()
                    val result = users.keys.find { it.username.contains(query, ignoreCase = true) }
                    result?.let {
                        printUserDetails(it)
                    } ?: println("\u001B[31m[Out]: User not found!\u001B[0m")
                }
                4 -> {
                    print("[In-String] Enter repository name to search: ")
                    val query = readln()
                    users.forEach { (user, repos) ->
                        repos.filter { it.name.contains(query, ignoreCase = true) }.forEach { repo ->
                            printRepoDetails(user, repo)
                        }
                    }
                }
                5 -> {
                    kotlin.system.exitProcess(0)
                }
                else -> println("\u001B[31m[Out]: Invalid Input\u001B[0m")
            }
        } catch (e: NumberFormatException) {
            println("\u001B[31m[Err]: Invalid Input\u001B[0m")
        }
    }
}
