package com.androidexpress.githubstars.ui.viewmodel

import com.androidexpress.githubstars.data.model.RepoItem

/**
 * Representa os possíveis estados da tela de listagem — a UI (Compose)
 * só precisa saber renderizar cada um destes três casos.
 */
sealed interface RepoListUiState {
    data object Loading : RepoListUiState
    data class Success(val repositories: List<RepoItem>) : RepoListUiState
    data class Error(val message: String) : RepoListUiState
}
