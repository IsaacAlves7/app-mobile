package com.androidexpress.githubstars.data.remote

import com.androidexpress.githubstars.data.model.RepoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {

    /**
     * Busca repositórios ordenados por número de estrelas (descendente).
     *
     * @param query        Query de busca no formato do GitHub Search API,
     *                      ex.: "stars:>1000" ou "stars:>1000 language:kotlin".
     * @param sort         Campo de ordenação (stars, forks, updated).
     * @param order        Direção da ordenação (desc/asc).
     * @param perPage      Quantidade de itens por página (máx. 100 na API do GitHub).
     * @param page         Página (paginação, começa em 1).
     */
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): RepoSearchResponse
}
