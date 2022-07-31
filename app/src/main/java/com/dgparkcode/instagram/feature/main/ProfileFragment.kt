package com.dgparkcode.instagram.feature.main

import android.os.Bundle
import android.view.View
import com.dgparkcode.instagram.base.BaseFragment
import com.dgparkcode.instagram.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}