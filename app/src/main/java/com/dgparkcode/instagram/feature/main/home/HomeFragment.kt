package com.dgparkcode.instagram.feature.main.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import com.dgparkcode.instagram.R
import com.dgparkcode.instagram.base.BaseFragment
import com.dgparkcode.instagram.databinding.FragmentHomeBinding
import com.dgparkcode.instagram.extension.repeatOnStarted
import com.dgparkcode.instagram.utils.SpaceItemDecoration
import com.dgparkcode.instagram.viewmodel.FollowingUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val followingUsersViewModel by viewModels<FollowingUsersViewModel>()
    private val followingUsersAdapter by lazy { FollowingUsersAdapter { onFollowingUserClicked(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupToolbarLogo()
        setupFollowingUserList()
    }

    private fun setupFollowingUserList() {
        binding.followingUserList.adapter = followingUsersAdapter

        val resources = context?.resources
        val spaceSize = resources?.getDimensionPixelSize(R.dimen.following_user_item_margin) ?: 0
        binding.followingUserList.addItemDecoration(SpaceItemDecoration(spaceSize))

        repeatOnStarted {
            followingUsersViewModel.followingUsersUiState.collect { state ->
                followingUsersAdapter.submitList(state.users)
            }
        }
    }

    private fun onFollowingUserClicked(position: Int) {
        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
    }

    private fun setupToolbarLogo() {
        val logoClickListener = View.OnClickListener { showCategoryPopup() }
        binding.logoImage.setOnClickListener(logoClickListener)
        binding.arrowImage.setOnClickListener(logoClickListener)
    }

    private fun setupToolbar() {
        binding.toolbar.inflateMenu(R.menu.fragment_home)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_new_post -> {
                    showNewPostPopup()
                    true
                }
                R.id.action_like -> {
                    Toast.makeText(requireContext(), "like", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_send -> {
                    Toast.makeText(requireContext(), "send", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun showCategoryPopup() {
        val popup = PopupMenu(requireContext(), binding.toolbar, GravityCompat.START)
        val menuInflater = popup.menuInflater
        menuInflater.inflate(R.menu.popup_home_category, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it?.itemId) {
                R.id.action_following -> {
                    Toast.makeText(requireContext(), "following", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_favorite -> {
                    Toast.makeText(requireContext(), "favorite", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popup.setForceShowIcon(true)
        popup.show()
    }

    private fun showNewPostPopup() {
        val popup = PopupMenu(requireContext(), binding.toolbar, GravityCompat.END)
        val menuInflater = popup.menuInflater
        menuInflater.inflate(R.menu.popup_home_new_post, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_posts -> {
                    Toast.makeText(requireContext(), "posts", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_story -> {
                    Toast.makeText(requireContext(), "story", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_reals -> {
                    Toast.makeText(requireContext(), "reals", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_livebr -> {
                    Toast.makeText(requireContext(), "live", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popup.setForceShowIcon(true)
        popup.show()
    }
}