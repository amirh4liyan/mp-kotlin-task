// Main.kt
package edu.sharif.kotlin

import edu.sharif.kotlin.data.model.APIError
import edu.sharif.kotlin.data.model.GitHubRepo
import edu.sharif.kotlin.data.model.GitHubUser
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException

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
        println("\tSelect Option from Menu: ")
        println("\t\t1) Get Data From Server Based on Username")
        println("\t\t2) Show Available Users")
        println("\t\t3) Search Based on Username")
        println("\t\t4) Search Based on Repository Name")
        println("\t\t5) Exit App")
        print("[In-Int]: Enter Item Code (1 -> 5): ")
        INPUT = readln()
        CODE = -1
        try {
            CODE = INPUT.toInt()
        } catch (e: NumberFormatException) {
            println("[Err]: Invalid Input")
        }

        when (CODE) {
            1 -> {
                val user = getUser("pouyayarandi")
                val repo = getRepos("pouyayarandi")
                println(user)
                println(repo)
                if (repo != null && user != null) {
                        users.put(user, repo)
                }
            }
            2 -> {
                users.forEach { (user, repos) ->
                    println("""
                    Username: ${user.username}
                    Followers: ${user.followersCount}
                    Following: ${user.followingCount}
                    Repositories: ${repos.size}
                    Joined: ${user.joinedDate}
                    ----------------------
                    """.trimIndent())
                }
            }
            3 -> {
                print("Enter username to search: ")
                val query = readln()
                val result = users.keys.find { it.username.equals(query, ignoreCase = true) }
                result?.let { user ->
                    println("User: ${user.username} found")
                } ?: println("User not found!")
            }
            4 -> {
                print("Enter repository name to search: ")
                val query = readln()
                users.forEach { (user, repos) ->
                    repos.filter { it.name.contains(query, ignoreCase = true) }.forEach { repo ->
                        println("""
                        User: ${user.username}
                        Repo: ${repo.name}
                        URL: ${repo.url}
                        ----------------------
                        """.trimIndent())
                    }
                }
            }
            5 -> {
                kotlin.system.exitProcess(0)
            }
            else -> println("[Out]: Invalid Input")
        }
    }
}
