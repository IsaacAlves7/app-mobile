package com.androidexpress.githubstars.data.model

import com.google.gson.annotations.SerializedName

/**
 * Corpo de resposta do endpoint de busca de repositórios do GitHub:
 * GET https://api.github.com/search/repositories
 */
data class RepoSearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val items: List<RepoItem>
)

/**
 * Um repositório retornado pela busca. O "score" pedido no briefing é
 * representado aqui pelo número de estrelas (stargazers_count), que é o
 * indicador de popularidade/impacto que o GitHub expõe publicamente.
 */
data class RepoItem(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("language") val language: String?,
    @SerializedName("owner") val owner: Owner
)

data class Owner(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)
