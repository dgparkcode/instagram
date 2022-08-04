package com.dgparkcode.instagram.feature.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.transform.CircleCropTransformation
import com.dgparkcode.instagram.R
import com.dgparkcode.instagram.databinding.ItemHomeFollowingUserBinding
import com.dgparkcode.instagram.databinding.ItemHomePostBinding
import com.dgparkcode.instagram.utils.SpaceItemDecoration
import com.dgparkcode.instagram.viewmodel.HomePostUiState

class HomePostsAdapter(
    private val context: Context
) : ListAdapter<HomePostUiState, RecyclerView.ViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HomePostUiState.FollowingUsers -> TYPE_HFU
            is HomePostUiState.Post -> TYPE_HP
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HFU -> {
                val binding = ItemHomeFollowingUserBinding.inflate(inflater, parent, false)
                HFUViewHolder(binding)
            }
            TYPE_HP -> {
                val binding = ItemHomePostBinding.inflate(inflater, parent, false)
                HPViewHolder(binding)
            }
            else -> {
                val binding = ItemHomePostBinding.inflate(inflater, parent, false)
                HPViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HFUViewHolder -> {
                holder.bind(getItem(position) as HomePostUiState.FollowingUsers)
            }
            is HPViewHolder -> {
                holder.bind(getItem(position) as HomePostUiState.Post)
            }
        }
    }

    companion object {
        private const val TYPE_HFU = 0
        private const val TYPE_HP = 1

        private val diffCallback = object : DiffUtil.ItemCallback<HomePostUiState>() {
            override fun areItemsTheSame(
                oldItem: HomePostUiState,
                newItem: HomePostUiState
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: HomePostUiState,
                newItem: HomePostUiState
            ): Boolean {
                return true
            }
        }
    }

    inner class HFUViewHolder(
        private val binding: ItemHomeFollowingUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomePostUiState.FollowingUsers) {
            val adapter = FollowingUsersAdapter {

            }
            adapter.submitList(item.users)
            binding.followingUserList.swapAdapter(adapter, true)

            if (binding.followingUserList.itemDecorationCount == 0) {
                val spaceSize = context.resources
                    .getDimensionPixelSize(R.dimen.following_user_item_margin)
                binding.followingUserList.addItemDecoration(SpaceItemDecoration(spaceSize))
            }
        }
    }

    inner class HPViewHolder(
        private val binding: ItemHomePostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomePostUiState.Post) {
            binding.userThumbImage.load(item.post.userImageUrl) {
                transformations(CircleCropTransformation())
            }
            binding.userNameText.text = item.post.userName

            val adapter = PostImagesAdapter()
            adapter.submitList(item.post.images)
            binding.contentsViewPager.adapter = adapter
            binding.contentsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            binding.likeCountText.text = item.post.likeCount
            binding.contentsText.text = item.post.contents
            binding.replyCountText.text = item.post.replyCount
            binding.lastReplyTimeText.text = item.post.lastReplyTime
        }
    }
}