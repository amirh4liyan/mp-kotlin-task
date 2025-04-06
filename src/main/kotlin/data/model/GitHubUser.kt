package edu.sharif.kotlin

data class GitHubUser(
    val user: String,
    val publicRepos: MutableList<String>,
    val JoinedDate: String,
    val followersCount: Int,
    val followingCount: Int
)