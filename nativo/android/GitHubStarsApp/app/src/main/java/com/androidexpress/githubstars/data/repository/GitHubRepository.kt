package com.androidexpress.githubstars.data.repository

import com.androidexpress.githubstars.data.model.RepoItem
import com.androidexpress.githubstars.data.remote.GitHubApiService

/**
 * Repositório de dados: isola o ViewModel dos detalhes de Retrofit/HTTP
 * e é o lugar certo para, no futuro, adicionar cache local (Room) sem
 * mudar quem consome esta classe.
 */
class GitHubRepository(
    private val api: GitHubApiService = com.androidexpress.githubstars.data.remote.RetrofitInstance.api
) {

    /**
     * Retorna os repositórios mais "impressionantes" do GitHub (mais
     * estrelas), opcionalmente filtrados por linguagem.
     *
     * A query "stars:>1000" é necessária porque a API de busca do GitHub
     * exige pelo menos um qualificador de busca — não dá pra simplesmente
     * pedir "todos os repositórios ordenados por estrela".
     */
    suspend fun getTopRepositories(language: String? = null, page: Int = 1): List<RepoItem> {
        val query = buildString {
            append("stars:>1000")
            if (!language.isNullOrBlank()) {
                append(" language:")
                append(language.trim())
            }
        }
        val response = api.searchRepositories(query = query, page = page)
        return response.items
    }
}
