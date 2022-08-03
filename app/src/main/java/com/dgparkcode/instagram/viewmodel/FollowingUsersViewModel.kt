package com.dgparkcode.instagram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgparkcode.instagram.entity.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingUsersViewModel @Inject constructor() : ViewModel() {

    private val _followingUsersUiState = MutableStateFlow(FollowingUsersUiState())
    val followingUsersUiState get() = _followingUsersUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _followingUsersUiState.update {
                FollowingUsersUiState(isLoading = true)
            }
            val users = (0..10).map {
                FollowingUserUiState(
                    id = it.toLong(),
                    imageUrl = "https://cdn.pixabay.com/photo/2015/11/16/14/43/cat-1045782__340.jpg",
                    name = "name $it"
                )
            }
            _followingUsersUiState.update {
                it.copy(isLoading = false, users = users)
            }
        }
    }
}

data class FollowingUsersUiState(
    val isLoading: Boolean = false,
    val users: List<FollowingUserUiState> = emptyList(),
    val userMessages: List<UserMessage> = emptyList()
)

data class FollowingUserUiState(
    val id: Long,
    val imageUrl: String,
    val name: String
)