package com.androidexpress.githubstars.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidexpress.githubstars.data.repository.GitHubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RepoListViewModel(
    private val repository: GitHubRepository = GitHubRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepoListUiState>(RepoListUiState.Loading)
    val uiState: StateFlow<RepoListUiState> = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private var currentLanguageFilter: String? = null

    init {
        loadTopRepositories()
    }

    fun loadTopRepositories(language: String? = currentLanguageFilter) {
        currentLanguageFilter = language
        viewModelScope.launch {
            _uiState.value = RepoListUiState.Loading
            fetchRepositories(language)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            fetchRepositories(currentLanguageFilter)
            _isRefreshing.value = false
        }
    }

    private suspend fun fetchRepositories(language: String?) {
        _uiState.value = try {
            val repos = repository.getTopRepositories(language = language)
            RepoListUiState.Success(repos)
        } catch (e: IOException) {
            RepoListUiState.Error("Sem conexão com a internet. Verifique sua rede.")
        } catch (e: HttpException) {
            if (e.code() == 403) {
                RepoListUiState.Error("Limite de requisições da API do GitHub atingido. Tente novamente em instantes.")
            } else {
                RepoListUiState.Error("Erro do servidor (código ${e.code()}).")
            }
        } catch (e: Exception) {
            RepoListUiState.Error("Algo deu errado: ${e.localizedMessage ?: "erro desconhecido"}")
        }
    }
}
