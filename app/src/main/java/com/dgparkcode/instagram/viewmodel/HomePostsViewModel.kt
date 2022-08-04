package com.dgparkcode.instagram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgparkcode.instagram.entity.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomePostsViewModel @Inject constructor() : ViewModel() {

    private val _homePosts = MutableStateFlow(HomePostsUiState())
    val homePosts: StateFlow<HomePostsUiState> get() = _homePosts.asStateFlow()

    init {
        viewModelScope.launch {

            _homePosts.update { it.copy(isLoading = true) }

            val hPosts = mutableListOf<HomePostUiState>()
            val followingUsers = (0..10)
                .map {
                    FollowingUserUiState(
                        userId = it.toLong(),
                        userImageUrl = "https://cdn.pixabay.com/photo/2013/05/30/18/21/cat-114782__340.jpg",
                        userName = "name$it"
                    )
                }
            hPosts.add(0, HomePostUiState.FollowingUsers(followingUsers))

            val posts = (0..10).map {
                val post = PostUiState(
                    userId = it.toLong(),
                    userImageUrl = "https://cdn.pixabay.com/photo/2014/03/29/09/17/cat-300572__340.jpg",
                    userName = "post$it",
                    images = listOf(
                        "https://cdn.pixabay.com/photo/2017/10/24/18/27/lion-2885618__340.jpg",
                        "https://cdn.pixabay.com/photo/2012/10/12/17/12/cat-61079__340.jpg",
                        "https://cdn.pixabay.com/photo/2017/08/07/12/27/cat-2603300__340.jpg",
                        "https://cdn.pixabay.com/photo/2015/06/19/14/20/cat-814952__340.jpg",
                        "https://cdn.pixabay.com/photo/2018/07/13/10/20/kittens-3535404__340.jpg"
                    ),
                    likeCount = "좋아요 1,000개",
                    contents = "귀여운 고양이!",
                    replyCount = "댓글 150개 모두 보기",
                    lastReplyTime = "1시간 전",

                )
                HomePostUiState.Post(post)
            }
            hPosts.addAll(posts)

            delay(1000)

            _homePosts.update { it.copy(isLoading = false, posts = hPosts) }
        }
    }
}

data class HomePostsUiState(
    val isLoading: Boolean = false,
    val posts: List<HomePostUiState> = emptyList(),
    val userMessages: List<UserMessage> = emptyList()
)

sealed class HomePostUiState {

    data class FollowingUsers(val users: List<FollowingUserUiState>) : HomePostUiState()

    data class Post(val post: PostUiState) : HomePostUiState()
}

data class FollowingUserUiState(
    val userId: Long,
    val userImageUrl: String,
    val userName: String
)

data class PostUiState(
    val userId: Long,
    val userImageUrl: String,
    val userName: String,
    val images: List<String>,
    val likeCount: String,
    val contents: String,
    val replyCount: String,
    val lastReplyTime: String
)