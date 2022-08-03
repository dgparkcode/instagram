package com.dgparkcode.instagram.feature.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dgparkcode.instagram.databinding.ItemFollowingUserBinding
import com.dgparkcode.instagram.viewmodel.FollowingUserUiState

class FollowingUsersAdapter(
    private val onItemClicked: (position: Int) -> Unit = {}
) : ListAdapter<FollowingUserUiState, FollowingUsersAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowingUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemFollowingUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FollowingUserUiState) {
            binding.thumbImage.load(item.imageUrl) { transformations(CircleCropTransformation()) }
            binding.nameText.text = item.name
            binding.root.setOnClickListener { onItemClicked(adapterPosition) }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FollowingUserUiState>() {
            override fun areItemsTheSame(
                oldItem: FollowingUserUiState,
                newItem: FollowingUserUiState
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FollowingUserUiState,
                newItem: FollowingUserUiState
            ): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.name == newItem.name &&
                        oldItem.imageUrl == newItem.imageUrl
            }
        }
    }
}